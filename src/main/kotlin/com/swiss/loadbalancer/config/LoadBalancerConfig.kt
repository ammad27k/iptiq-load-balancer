package com.swiss.loadbalancer.config

class LoadBalancerConfig{
    var config: Config? = null
}

class Config {
    var provider: List<Provider>? = null
    var maxLimit : Int? = null
    var loadBalancerType : LoadBalancerType? = null
}

class Provider {
    var id: Int? = null
    var name: String? = null
    var methodName: String? = null
    var maxParallelConnection: Int? = null
}

enum class LoadBalancerType{
    ROUND_ROBIN,
    RANDOM
}