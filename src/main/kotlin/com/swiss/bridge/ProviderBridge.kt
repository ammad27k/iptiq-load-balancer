package com.swiss.bridge

import com.swiss.providers.service.ProviderService

class ProviderBridge(providersInfo: MutableList<ProviderData>) {

    companion object {
        var providerInstances : MutableList<ProviderService> = mutableListOf()
        var providerInfo : MutableList<ProviderData> = mutableListOf()
        var unHealthyProviders : MutableList<ProviderData> = mutableListOf()
    }

    init {
        for(provider in providersInfo) {
            providerInstances.add(ProviderService(provider.id, provider.name))
            providerInfo.add(
                ProviderData(
                    provider.id,
                    provider.name,
                    provider.methodName,
                    provider.maxParallelConnection
                )
            )
        }
    }
}