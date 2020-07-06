package com.sedymov.aviasales.presentation.base.view

import moxy.viewstate.strategy.alias.AddToEndSingle

interface BaseMoxyViewWithTitle : BaseMoxyView {

    @AddToEndSingle
    fun setTitle(title: String)
}