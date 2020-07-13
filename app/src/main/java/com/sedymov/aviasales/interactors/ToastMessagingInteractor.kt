package com.sedymov.aviasales.interactors

import android.content.Context
import android.widget.Toast
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor

class ToastMessagingInteractor(private val context: Context) : MessagingInteractor {

    override fun showErrorMessage(message: String) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}