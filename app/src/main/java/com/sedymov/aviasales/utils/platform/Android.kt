package com.sedymov.aviasales.utils.platform

import com.github.moxy_community.moxy.androidx.BuildConfig


inline fun runInDebugBuild(r: () -> Any) {

    if (BuildConfig.DEBUG) {

        r.invoke()
    }
}