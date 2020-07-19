package com.sedymov.aviasales.presentation.base.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution

interface MoxyViewWithErrorMessageSupport : MvpView {

    @OneExecution
    fun showErrorMessage(message: String)
}