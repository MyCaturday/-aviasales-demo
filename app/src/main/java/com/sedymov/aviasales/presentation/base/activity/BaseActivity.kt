package com.sedymov.aviasales.presentation.base.activity

import android.os.Bundle
import com.sedymov.aviasales.App
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithOnBackPressedListener
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.mountActivity(this)
        inject()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun inject()
}