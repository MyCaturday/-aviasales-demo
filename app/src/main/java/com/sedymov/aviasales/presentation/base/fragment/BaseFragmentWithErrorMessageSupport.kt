package com.sedymov.aviasales.presentation.base.fragment

import android.widget.Toast
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.presentation.base.view.ViewWithErrorMessageSupport
import com.sedymov.aviasales.utils.platform.ERROR_MESSAGE_DURATION
import com.tapadoo.alerter.Alerter

abstract class BaseFragmentWithErrorMessageSupport : BaseFragmentWithOnBackPressedListener(), ViewWithErrorMessageSupport {

    override fun showErrorMessage(message: String) {

        activity?.let { activity ->

            Alerter.create(activity)
                .setText(message)
                .setBackgroundColorRes(R.color.errorMessageBackground)
                .setIcon(R.drawable.ic_error)
                .setDuration(ERROR_MESSAGE_DURATION)
                .show()

        } ?: run {

            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}