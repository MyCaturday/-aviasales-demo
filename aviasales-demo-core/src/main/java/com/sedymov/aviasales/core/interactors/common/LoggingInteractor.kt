package com.sedymov.aviasales.core.interactors.common

interface LoggingInteractor {

    fun verbose(tag: String, message: String)

    fun debug(tag: String, message: String)

    fun info(tag: String, message: String)

    fun warn(tag: String, message: String)

    fun error(tag: String, message: String)

    fun error(tag: String, message: String, t: Throwable)

    fun wtf(tag: String, message: String)
}