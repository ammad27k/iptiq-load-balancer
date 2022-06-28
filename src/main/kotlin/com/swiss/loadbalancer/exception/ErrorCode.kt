package com.swiss.loadbalancer.exception

interface ErrorCode {
    fun code(): String
}

enum class ErrorCodes : ErrorCode {
    MAX_PROVIDER_LIMIT_REACHED,
    MAX_CONNECTION_REACHED_FOR_PROVIDER,
    NO_HEALTHY_PROVIDER;

    override fun code(): String = this.name
}