package com.swiss.route

import com.swiss.bridge.ProviderBridge
import com.swiss.bridge.ProviderData
import com.swiss.loadbalancer.exception.ApplicationException
import com.swiss.loadbalancer.exception.ErrorCodes
import java.util.concurrent.atomic.AtomicInteger

class Router {

    companion object {
         val providerMap : MutableMap<ProviderData, AtomicInteger> = mutableMapOf()
    }

    init {
        if(providerMap.isEmpty()) {
            ProviderBridge.providerInfo.forEach { providerData ->
                providerMap[providerData] = AtomicInteger()
            }
        }
    }

    fun get(id : Int) : String {

        val provider = providerMap.keys.first {
            it.id == id
        }

        val numberOfConnections = providerMap[provider]?.incrementAndGet()
        if(provider.maxParallelConnection!! < numberOfConnections!!) {
            throw ApplicationException(ErrorCodes.MAX_CONNECTION_REACHED_FOR_PROVIDER, "Max connection reached for provider ${provider.id}")
        }


        // call providerService Based on id, once done decrement
        val providerService = ProviderBridge.providerInstances.first {
            it.id == id
        }
        return providerService.get().let {
            providerMap[provider]?.decrementAndGet()
            it
        }
    }

    fun check(id : Int) : Boolean {
        val providerService = ProviderBridge.providerInstances.first {
            it.id == id
        }
        return providerService.check()
    }
}