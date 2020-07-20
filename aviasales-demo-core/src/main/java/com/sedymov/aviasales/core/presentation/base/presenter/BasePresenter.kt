package com.sedymov.aviasales.core.presentation.base.presenter

import com.sedymov.aviasales.core.presentation.base.view.BaseView
import moxy.MvpPresenter

abstract class BasePresenter <V : BaseView> : MvpPresenter<V>()