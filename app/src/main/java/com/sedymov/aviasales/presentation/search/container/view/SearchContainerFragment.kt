package com.sedymov.aviasales.presentation.search.container.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.presentation.search.container.presenter.SearchContainerPresenter
import com.sedymov.aviasales.core.presentation.search.container.view.SearchContainerView
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.di.search.SearchComponent.Companion.SEARCH_SCOPE
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithOnBackPressedListener
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

class SearchContainerFragment : BaseFragmentWithOnBackPressedListener(), SearchContainerView {

    private val mNavigator: Navigator by lazy { SupportAppNavigator(activity!!, childFragmentManager, R.id.fragment_frame) }

    @Inject
    @field:Named(SEARCH_SCOPE)
    internal lateinit var mNavigatorHolder: NavigatorHolder

    @Inject
    internal lateinit var mPresenterProvider: Provider<SearchContainerPresenter>

    private val mPresenter: SearchContainerPresenter by moxyPresenter { mPresenterProvider.get() }

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