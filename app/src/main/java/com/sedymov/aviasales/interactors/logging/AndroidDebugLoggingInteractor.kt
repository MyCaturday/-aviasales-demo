package com.sedymov.aviasales.interactors.logging

import com.sedymov.aviasales.utils.platform.runInDebugBuild

class AndroidDebugLoggingInteractor : AndroidLoggingInteractor() {

    private inline fun runInDebug(r: () -> Any) = runInDebugBuild(r)

    override fun verbose(tag: String, message: String) {
        runInDebug { super.verbose(tag, message) }
    }

    override fun debug(tag: String, message: String) {
        runInDebug { super.debug(tag, message) }
    }

    override fun info(tag: String, message: String) {
        runInDebug { super.info(tag, message) }
    }

    override fun warn(tag: String, message: String) {
        runInDebug { super.warn(tag, message) }
    }

    override fun error(tag: String, message: String) {
        runInDebug { super.error(tag, message) }
    }

    override fun error(tag: String, message: String, throwable: Throwable) {
        runInDebug { super.error(tag, message, throwable) }
    }

    override fun wtf(tag: String, message: String) {
        runInDebug { super.wtf(tag, message) }
    }
}