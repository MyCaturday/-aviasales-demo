package com.sedymov.aviasales.presentation.base.fragment

import android.os.Bundle
import com.sedymov.aviasales.presentation.base.view.BaseMoxyView
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseMoxyView {

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun inject()
}