package com.swiss.loadbalancer.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import mu.KotlinLogging

class LoadBalancerConfigInitializer {

    private val logger = KotlinLogging.logger {}
    companion object {
        var loadBalancerConfig : LoadBalancerConfig? = null
    }

    init {
        try {
            val mapper = ObjectMapper(YAMLFactory())
            val configFileStream =
                javaClass.classLoader.getResourceAsStream("load-balancer-config.yml")
            loadBalancerConfig = mapper.readValue(configFileStream, LoadBalancerConfig::class.java)
            logger.debug("config initialized successfully")
        } catch (e : Exception) {
            logger.error("#LoadBalancerConfigInitializer# Config not initialized", e)
        }
    }
}