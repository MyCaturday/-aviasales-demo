package com.sedymov.aviasales.presentation.base.activity

import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithOnBackPressedListener

abstract class BaseActivityWithOnBackPressedListener : BaseActivity(), BaseFragmentWithOnBackPressedListener.Host  {

    protected var backPressedListener: BaseFragmentWithOnBackPressedListener.Host.OnBackPressedListenerListener? = null

    override fun setOnBackPressedListener(listener: BaseFragmentWithOnBackPressedListener.Host.OnBackPressedListenerListener?) {

        backPressedListener = listener
    }

    override fun onBackPressed() {

        val shouldBreak = backPressedListener?.onBackPressed() ?: false
        if (!shouldBreak) {
            super.onBackPressed()
        }
    }
}