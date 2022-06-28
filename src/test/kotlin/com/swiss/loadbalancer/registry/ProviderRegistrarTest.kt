package com.swiss.loadbalancer.registry

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ProviderRegistrarTest {

    lateinit var underTest : ProviderRegistrar

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun registerProvider() {
        underTest = ProviderRegistrar()
        assertNotNull(underTest.providers)
    }
}