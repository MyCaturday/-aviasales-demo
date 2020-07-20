package com.sedymov.aviasales.presentation.main.view

import android.os.Bundle
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.main.navigation.MainRouter
import com.sedymov.aviasales.core.presentation.main.presenter.MainPresenter
import com.sedymov.aviasales.core.presentation.main.view.MainView
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.di.main.MainComponent.Companion.MAIN_SCOPE
import com.sedymov.aviasales.presentation.base.activity.BaseActivityWithOnBackPressedListener
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import javax.inject.Named

import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : BaseActivityWithOnBackPressedListener(), MainView {

    private val mNavigator: Navigator by lazy { SupportAppNavigator(this, R.id.fragment_frame) }

    @Inject
    internal lateinit var mLoggingInteractor: LoggingInteractor

    @Inject
    internal lateinit var mRouter: MainRouter

    @Inject
    @field:Named(MAIN_SCOPE)
    internal lateinit var mNavigatorHolder: NavigatorHolder

    @InjectPresenter
    internal lateinit var mPresenter: MainPresenter

    @ProvidePresenter
    internal fun providePresenter(): MainPresenter = MainPresenter(mLoggingInteractor, mRouter)

    override fun inject() = ComponentStorage.getInstance().mainComponent.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        mNavigatorHolder.setNavigator(mNavigator)
    }

    override fun onPause() {
        super.onPause()
        mNavigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            ComponentStorage.getInstance().clearMainComponent()
        }
    }
}