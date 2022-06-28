package com.swiss.loadbalancer.checker

import com.swiss.route.Router
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ProviderHealthCheckerScheduler {

    private val executors = Executors.newScheduledThreadPool(2)
    private val providersHealthChecker = ProvidersHealthChecker(Router())

    private fun removeUnHealthyProviders() {
        executors.scheduleAtFixedRate({
            providersHealthChecker.removeUnHealthyProvider()
        }, 0,10, TimeUnit.SECONDS)
    }

    private fun addHealthyProviders() {
        executors.scheduleAtFixedRate({
            providersHealthChecker.addUnHealthyProviderIfBecomesAvailable()
        }, 0,10, TimeUnit.SECONDS)
    }

    init {
        removeUnHealthyProviders()
       addHealthyProviders()
    }
}