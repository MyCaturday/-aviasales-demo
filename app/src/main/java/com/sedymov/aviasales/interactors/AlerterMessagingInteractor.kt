package com.sedymov.aviasales.interactors

import com.sedymov.aviasales.App
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.utils.platform.ERROR_MESSAGE_DURATION
import com.tapadoo.alerter.Alerter

class AlerterMessagingInteractor: MessagingInteractor {

    override fun showErrorMessage(message: String) {

        App.instance.getActivity()?.let { activity ->

            Alerter.create(activity)
                .setText(message)
                .setBackgroundColorRes(R.color.errorMessageBackground)
                .setDuration(ERROR_MESSAGE_DURATION)
                .show()
        }
    }
}