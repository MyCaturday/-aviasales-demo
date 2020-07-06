package com.sedymov.aviasales.presentation.base.fragment

import androidx.appcompat.app.AppCompatActivity
import com.sedymov.aviasales.presentation.base.view.BaseMoxyViewWithTitle

abstract class BaseFragmentWithTitle : BaseFragmentWithOnBackPressedListener(), BaseMoxyViewWithTitle {

    override fun setTitle(title: String) {

        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }
}