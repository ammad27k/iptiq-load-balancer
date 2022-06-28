package com.swiss.loadbalancer.types

import com.swiss.loadbalancer.checker.ProviderHealthCheckerScheduler
import com.swiss.loadbalancer.config.LoadBalancerConfigInitializer
import com.swiss.loadbalancer.registry.ProviderRegistrar
import com.swiss.route.Router
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class RoundRobinLoadBalancerTest {

    lateinit var underTest : RoundRobinLoadBalancer

    @BeforeEach
    fun setUp() {
        LoadBalancerConfigInitializer()
        ProviderRegistrar()
        ProviderHealthCheckerScheduler()
        underTest = RoundRobinLoadBalancer(Router())
    }

    @Test
    fun validateGetMethod() {
        val result = underTest.get()
        assertNotNull(result)
    }


}