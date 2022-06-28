package com.swiss.loadbalancer.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory

class LoadBalancerConfigInitializer {

    companion object {
        var loadBalancerConfig : LoadBalancerConfig? = null
    }

    init {
        val mapper = ObjectMapper(YAMLFactory())
        val configFileStream =
            javaClass.classLoader.getResourceAsStream("load-balancer-config.yml")
        loadBalancerConfig = mapper.readValue(configFileStream, LoadBalancerConfig::class.java)
    }
}