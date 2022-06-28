package com.swiss

import com.swiss.loadbalancer.checker.ProviderHealthCheckerScheduler
import com.swiss.loadbalancer.config.LoadBalancerConfigInitializer
import com.swiss.loadbalancer.factory.LoadBalancerFactory
import com.swiss.loadbalancer.registry.ProviderRegistrar
import java.util.concurrent.CompletableFuture

fun main() {

    LoadBalancerConfigInitializer()
    ProviderRegistrar()
    ProviderHealthCheckerScheduler()
    val loadBalancer = LoadBalancerFactory.getLoadBalancer(LoadBalancerConfigInitializer.loadBalancerConfig?.config?.loadBalancerType!!)


    /*val executors = Executors.newFixedThreadPool(20)
    CompletableFuture.supplyAsync {  }
    CompletableFuture.allOf()
    for(i in 1..20) {
        executors.submit {
            try {
                println(loadBalancer.get())
            }catch (e : ApplicationException) {
                println(e.message)
            }
        }
    }*/
    val tasksList: MutableList<CompletableFuture<String>?> = mutableListOf()
    for(i in 1..20) {
        val tasks = CompletableFuture.supplyAsync {
            val result = loadBalancer.get()
            println(result)
            result
        }.exceptionally { e -> println(e)
            ""
        }
        tasksList.add(tasks)
    }
}