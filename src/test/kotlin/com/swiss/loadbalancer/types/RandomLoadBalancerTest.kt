package com.swiss.loadbalancer.types

import com.swiss.loadbalancer.checker.ProviderHealthCheckerScheduler
import com.swiss.loadbalancer.config.LoadBalancerConfigInitializer
import com.swiss.loadbalancer.registry.ProviderRegistrar
import com.swiss.route.Router
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RandomLoadBalancerTest {

    lateinit var underTest : RandomLoadBalancer

    @BeforeEach
    fun setUp() {
        LoadBalancerConfigInitializer()
        ProviderRegistrar()
        ProviderHealthCheckerScheduler()
        underTest = RandomLoadBalancer(Router())
    }

    @Test
    fun validateGetMethod() {
        val result = underTest.get()
        assertNotNull(result)
    }
}