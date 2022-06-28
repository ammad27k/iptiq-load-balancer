package com.swiss.loadbalancer.registry

import com.swiss.bridge.ProviderBridge
import com.swiss.bridge.ProviderData
import com.swiss.loadbalancer.config.LoadBalancerConfigInitializer
import com.swiss.loadbalancer.exception.ApplicationException
import com.swiss.loadbalancer.exception.ErrorCodes


class ProviderRegistrar {
    var providers: MutableList<ProviderData> = mutableListOf()


    private fun registerProvider(provider : ProviderData, maxLimit : Int) {
        if(providers.size > maxLimit) throw ApplicationException(ErrorCodes.MAX_PROVIDER_LIMIT_REACHED,
            "Max limit reached to add providers")

        providers.add(provider)
    }

    init {
        val loadBalancerConfig = LoadBalancerConfigInitializer.loadBalancerConfig
        loadBalancerConfig?.config?.provider?.forEach {provider ->
            registerProvider(ProviderData(
                provider.id,
                provider.name,
                provider.methodName,
                provider.maxParallelConnection
            ), loadBalancerConfig?.config?.maxLimit!!) // remove this !! to handle exception properly TODO
        }
        ProviderBridge(providers)
    }
}