package com.sedymov.aviasales

import android.app.Activity
import android.app.Application
import java.lang.ref.WeakReference

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        @JvmStatic
        lateinit var instance: App
            private set
    }
}