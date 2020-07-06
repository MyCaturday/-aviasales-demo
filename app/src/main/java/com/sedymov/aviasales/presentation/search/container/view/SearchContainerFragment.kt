package com.sedymov.aviasales.presentation.search.container.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.di.search.SearchComponent.Companion.SEARCH_SCOPE
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithOnBackPressedListener
import com.sedymov.aviasales.presentation.search.container.presenter.SearchContainerMoxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject
import javax.inject.Named

class SearchContainerFragment : BaseFragmentWithOnBackPressedListener(), SearchContainerMoxyView {

    private val mNavigator: Navigator by lazy { SupportAppNavigator(activity!!, childFragmentManager, R.id.fragment_frame) }

    @Inject
    internal lateinit var mLoggingInteractor: LoggingInteractor

    @Inject
    internal lateinit var mSearchRouter: SearchRouter

    @Inject
    @field:Named(SEARCH_SCOPE)
    internal lateinit var mNavigatorHolder: NavigatorHolder

    @InjectPresenter
    internal lateinit var mPresenter: SearchContainerMoxyPresenter

    @ProvidePresenter
    internal fun providePresenter(): SearchContainerMoxyPresenter = SearchContainerMoxyPresenter(mLoggingInteractor, mSearchRouter)

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_search_container, container, false)

    override fun onResume() {
        super.onResume()
        mNavigatorHolder.setNavigator(mNavigator)
    }

    override fun onPause() {
        super.onPause()
        mNavigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.let { activity ->

            if (activity.isFinishing) {

                ComponentStorage.getInstance().clearSearchComponent()
            }
        }
    }

    companion object {

        fun newInstance(): SearchContainerFragment {
            return SearchContainerFragment().apply {
                arguments = Bundle()
            }
        }
    }
}