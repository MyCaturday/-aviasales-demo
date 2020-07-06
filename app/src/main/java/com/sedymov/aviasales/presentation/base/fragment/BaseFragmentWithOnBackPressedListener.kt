package com.sedymov.aviasales.presentation.base.fragment

import android.content.Context

abstract class BaseFragmentWithOnBackPressedListener : BaseFragment() {

    interface Host {

        interface OnBackPressedListenerListener {

            fun onBackPressed(): Boolean // return true if the listener handled the back press
        }

        fun setOnBackPressedListener(listener: OnBackPressedListenerListener?)
    }

    private var host: Host? = null
    private var onBackPressedListenerListener: Host.OnBackPressedListenerListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        onBackPressedListenerListener = object : Host.OnBackPressedListenerListener {

            override fun onBackPressed(): Boolean {

                return this@BaseFragmentWithOnBackPressedListener.onBackPressed()
            }
        }

        host = context as? Host
        host?.setOnBackPressedListener(onBackPressedListenerListener)
    }

    override fun onDetach() {
        super.onDetach()

        host?.setOnBackPressedListener(null)
        host = null
    }

    protected open fun onBackPressed(): Boolean = false
}