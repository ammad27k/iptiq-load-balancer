package com.swiss.loadbalancer.types

import com.swiss.bridge.ProviderBridge
import com.swiss.bridge.ProviderData
import com.swiss.loadbalancer.LoadBalancer
import com.swiss.loadbalancer.exception.ApplicationException
import com.swiss.loadbalancer.exception.ErrorCodes
import com.swiss.route.Router
import kotlin.random.Random

class RandomLoadBalancer(private val router : Router) : LoadBalancer {

    var providersList : MutableList<ProviderData>? = null

    override fun get(): String {
        val unhealthyProviders = ProviderBridge.unHealthyProviders.map { it.id }.toTypedArray()

        val healthyProviders = providersList?.filter { it.id !in unhealthyProviders }
        if(healthyProviders?.isEmpty() == true)  throw ApplicationException(ErrorCodes.NO_HEALTHY_PROVIDER, "No Up stream service available")

        val random = Random.nextInt(0,healthyProviders?.size!!)
        val provider = healthyProviders[random]
        return router.get(provider.id!!)
    }

    init {
        providersList = ProviderBridge.providerInfo
    }
}