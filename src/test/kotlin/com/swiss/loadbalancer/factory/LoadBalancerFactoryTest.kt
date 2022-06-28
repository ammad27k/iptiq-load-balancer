package com.swiss.loadbalancer.factory

import com.swiss.loadbalancer.config.LoadBalancerType
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LoadBalancerFactoryTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun createAndVerifySingletonObjectExistForRoundRobinLoanBalancer() {
        val loadBalancer = LoadBalancerFactory.getLoadBalancer(LoadBalancerType.ROUND_ROBIN)
        assertNotNull(LoadBalancerFactory.loadBalancer)
        assertEquals(loadBalancer == LoadBalancerFactory.loadBalancer, true)
    }
}