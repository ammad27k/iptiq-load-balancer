package com.swiss.loadbalancer.factory

import com.swiss.loadbalancer.LoadBalancer
import com.swiss.loadbalancer.config.LoadBalancerType
import com.swiss.loadbalancer.types.RandomLoadBalancer
import com.swiss.loadbalancer.types.RoundRobinLoadBalancer
import com.swiss.route.Router

class LoadBalancerFactory {

    companion object {

        var loadBalancer : LoadBalancer? = null

        fun getLoadBalancer(loanBalancerType : LoadBalancerType) : LoadBalancer {
            loadBalancer =  loadBalancer?: when(loanBalancerType) {
                LoadBalancerType.ROUND_ROBIN -> {
                    RoundRobinLoadBalancer(Router())
                }
                LoadBalancerType.RANDOM -> {
                    RandomLoadBalancer(Router())
                }
            }
            return loadBalancer as LoadBalancer
        }
    }
}