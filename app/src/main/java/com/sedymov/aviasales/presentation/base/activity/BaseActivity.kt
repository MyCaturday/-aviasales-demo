package com.sedymov.aviasales.presentation.base.activity

import android.os.Bundle
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithOnBackPressedListener
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun inject()
}