package com.sedymov.aviasales.utils.platform

import com.sedymov.aviasales.BuildConfig


fun isLollipop(): Boolean {

    return android.os.Build.VERSION.SDK_INT.let { osVersion ->

        osVersion >= android.os.Build.VERSION_CODES.LOLLIPOP && osVersion < android.os.Build.VERSION_CODES.M
    }
}

inline fun runInDebugBuild(r: () -> Any) {

    if (BuildConfig.DEBUG) {

        r.invoke()
    }
}