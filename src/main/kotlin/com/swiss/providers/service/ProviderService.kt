package com.swiss.providers.service

import java.util.*


class ProviderService (
    val id : Int?,
    private val name : String?){

    fun get() : String {
        Thread.sleep(1000)
        return "Name -> $name, Identifier -> " + UUID.randomUUID()
    }

    fun check() : Boolean {
        return true
    }
}