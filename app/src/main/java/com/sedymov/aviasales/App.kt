package com.sedymov.aviasales

import android.app.Activity
import android.app.Application
import java.lang.ref.WeakReference

class App : Application() {

    private lateinit var mActivityRef: WeakReference<Activity>

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun mountActivity(activity: Activity) {

        mActivityRef = WeakReference<Activity>(activity)
    }

    fun getActivity(): Activity? = mActivityRef.get()

    companion object {

        @JvmStatic
        lateinit var instance: App
            private set
    }
}