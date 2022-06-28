package com.swiss.loadbalancer

import com.swiss.route.Router

interface LoadBalancer {

    fun get() : String
}