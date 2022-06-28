package com.swiss.loadbalancer.types

import com.swiss.bridge.ProviderBridge
import com.swiss.bridge.ProviderData
import com.swiss.loadbalancer.LoadBalancer
import com.swiss.loadbalancer.exception.ApplicationException
import com.swiss.loadbalancer.exception.ErrorCodes
import com.swiss.route.Router
import java.util.concurrent.locks.ReentrantLock

class RoundRobinLoadBalancer(private val router : Router) : LoadBalancer {

    var counter : Int = 0;
    var providersList : MutableList<ProviderData>? = null
    var lock : ReentrantLock? = null

    override fun get(): String {
        lock?.lock()
        try {
            val unhealthyProviders = ProviderBridge.unHealthyProviders.map { it.id }.toTypedArray()

            val healthyProviders = providersList?.filter { it.id !in unhealthyProviders }
            if(healthyProviders?.isEmpty() == true)  throw ApplicationException(ErrorCodes.NO_HEALTHY_PROVIDER, "No Up stream service available")

            val provider = healthyProviders?.get(counter)
            counter++
            if(counter == healthyProviders?.size) {
                counter = 0
            }
            return router.get(provider?.id!!) // TODO remove !!
        } finally {
            lock?.unlock()
        }
    }

    init {
        providersList = ProviderBridge.providerInfo
        lock = ReentrantLock()
    }
}