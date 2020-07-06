package com.sedymov.aviasales.presentation.main.presenter

import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.base.presenter.BasePresenter
import com.sedymov.aviasales.core.presentation.base.view.BaseView
import com.sedymov.aviasales.core.presentation.main.navigation.MainRouter
import com.sedymov.aviasales.core.presentation.main.presenter.MainPresenter
import com.sedymov.aviasales.core.presentation.main.view.MainView
import com.sedymov.aviasales.presentation.base.presenter.BaseMoxyPresenter
import com.sedymov.aviasales.presentation.main.view.MainMoxyView
import moxy.InjectViewState

@InjectViewState
class MainMoxyPresenter(
    private val mLoggingInteractor: LoggingInteractor,
    private val mRouter: MainRouter
): BaseMoxyPresenter<MainMoxyView>(), MainView {

    override fun providePresenter() = MainPresenter(mLoggingInteractor, mRouter) as BasePresenter<BaseView>

    private inline fun getPresenter(): MainPresenter =
        mPresenter as MainPresenter
}