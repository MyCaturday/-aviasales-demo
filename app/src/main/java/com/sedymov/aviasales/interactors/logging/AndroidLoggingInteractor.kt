package com.sedymov.aviasales.interactors.logging

import android.util.Log
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor


open class AndroidLoggingInteractor : LoggingInteractor {

    override fun verbose(tag: String, message: String) {
        Log.v(tag, message)
    }

    override fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun info(tag: String, message: String) {
        Log.i(tag, message)
    }

    override fun warn(tag: String, message: String) {
        Log.w(tag, message)
    }

    override fun error(tag: String, message: String) {
        Log.e(tag, message)
    }

    override fun error(tag: String, message: String, throwable: Throwable) {
        Log.e(tag, message, throwable)
    }

    override fun wtf(tag: String, message: String) {
        Log.wtf(tag, message)
    }
}