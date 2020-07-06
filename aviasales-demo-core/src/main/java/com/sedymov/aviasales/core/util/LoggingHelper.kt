package com.sedymov.aviasales.core.util

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor

class LoggingHelper(private val mDefaultLoggingTag: String, private val mLoggingInteractor: LoggingInteractor) {

    fun v(message: String) {

        v(mDefaultLoggingTag, message)
    }

    fun v(tag: String, message: String) {

        mLoggingInteractor.verbose(tag, message)
    }

    fun d(message: String) {

        d(mDefaultLoggingTag, message)
    }

    fun d(tag: String, message: String) {

        mLoggingInteractor.debug(tag, message)
    }

    fun i(message: String) {

        i(mDefaultLoggingTag, message)
    }

    fun i(tag: String, message: String) {

        mLoggingInteractor.info(tag, message)
    }

    fun w(message: String) {

        w(mDefaultLoggingTag, message)
    }

    fun w(tag: String, message: String) {

        mLoggingInteractor.warn(tag, message)
    }

    fun e(message: String) {

        e(mDefaultLoggingTag, message)
    }

    fun e(tag: String, message: String) {

        mLoggingInteractor.error(tag, message)
    }

    fun e(tag: String, message: String, t: Throwable) {

        mLoggingInteractor.error(tag, message, t)
    }

    fun e(t: Throwable) {

        mLoggingInteractor.error(mDefaultLoggingTag, "", t)
    }

    fun wtf(message: String) {

        wtf(mDefaultLoggingTag, message)
    }

    fun wtf(tag: String, message: String) {

        mLoggingInteractor.wtf(tag, message)
    }
}