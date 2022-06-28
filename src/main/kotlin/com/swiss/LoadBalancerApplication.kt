package com.swiss

import com.swiss.loadbalancer.checker.ProviderHealthCheckerScheduler
import com.swiss.loadbalancer.config.LoadBalancerConfigInitializer
import com.swiss.loadbalancer.factory.LoadBalancerFactory
import com.swiss.loadbalancer.registry.ProviderRegistrar
import mu.KotlinLogging
import java.util.concurrent.CompletableFuture

private val logger = KotlinLogging.logger {}
fun main() {

    LoadBalancerConfigInitializer()
    ProviderRegistrar()
    ProviderHealthCheckerScheduler()
    val loadBalancer = LoadBalancerFactory.getLoadBalancer(LoadBalancerConfigInitializer.loadBalancerConfig?.config?.loadBalancerType!!)


    val tasksList: MutableList<CompletableFuture<String>?> = mutableListOf()
    for(i in 1..20) {
        val tasks = CompletableFuture.supplyAsync {
            val result = loadBalancer.get()
            logger.debug(result)
            result
        }.exceptionally { e -> println(e)
            ""
        }
        tasksList.add(tasks)
    }
}