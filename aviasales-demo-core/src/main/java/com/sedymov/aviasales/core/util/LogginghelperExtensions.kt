package com.sedymov.aviasales.core.util

fun LoggingHelper.currentThreadName() {

    v(Thread.currentThread().name)
}