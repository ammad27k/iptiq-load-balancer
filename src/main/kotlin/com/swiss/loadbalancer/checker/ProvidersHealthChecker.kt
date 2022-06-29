package com.swiss.loadbalancer.checker

import com.swiss.bridge.ProviderBridge
import com.swiss.bridge.ProviderData
import com.swiss.route.Router
import mu.KotlinLogging
import java.util.concurrent.locks.ReentrantLock

class ProvidersHealthChecker(private val router : Router) {

    private val lock: ReentrantLock = ReentrantLock()
    private val logger = KotlinLogging.logger {}

    fun removeUnHealthyProvider() {
        lock.lock()
        try {
            ProviderBridge.providerInfo.forEach {
                val result = router.check(it.id!!)
                if(!result) {
                    ProviderBridge.unHealthyProviders.add(it)
                }
            }
        } catch (e : Exception) {
            logger.error("#ProvidersHealthChecker# removeUnHealthyProvider ", e)
        }finally {
            lock.unlock()
        }
    }

    fun addUnHealthyProviderIfBecomesAvailable() {
        lock.lock()
        try {
            var removeUnHealthyProviders = mutableListOf<ProviderData>()
            ProviderBridge.unHealthyProviders.forEach {

                var counter = 1
                var result = false
                while(counter <= 2) {
                    result = router.check(it.id!!)
                    counter++
                }
                if(result) {
                    removeUnHealthyProviders.add(it)
                }
            }
            removeUnHealthyProviders.forEach {
                ProviderBridge.unHealthyProviders.remove(it)
            }
        } catch (e : Exception) {
            logger.error("#ProvidersHealthChecker# addUnHealthyProviderIfBecomesAvailable ", e)
        }finally {
            lock.unlock()
        }
    }

}