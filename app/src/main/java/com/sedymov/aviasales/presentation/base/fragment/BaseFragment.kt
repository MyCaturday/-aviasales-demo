package com.sedymov.aviasales.presentation.base.fragment

import android.os.Bundle
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun inject()
}