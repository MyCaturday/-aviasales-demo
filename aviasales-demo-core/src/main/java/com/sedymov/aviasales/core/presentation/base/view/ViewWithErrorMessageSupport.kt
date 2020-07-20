package com.sedymov.aviasales.core.presentation.base.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution

interface ViewWithErrorMessageSupport : MvpView {

    @OneExecution
    fun showErrorMessage(message: String)
}