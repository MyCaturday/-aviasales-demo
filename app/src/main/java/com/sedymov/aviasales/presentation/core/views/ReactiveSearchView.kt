package com.sedymov.aviasales.presentation.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.sedymov.aviasales.utils.platform.hideKeyboard
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sedymov.aviasales.R
import com.sedymov.aviasales.utils.platform.SEARCH_TIMER_DELAY_MILLISECONDS
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class ReactiveSearchView : LinearLayout {

    lateinit var searchField: EditText
    private var mainView: View? = null

    private val onEmptyInputSubject = PublishSubject.create<Any>()
    private val onCancelSubject = PublishSubject.create<Any>()

    constructor(context: Context) : super(context) {

        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet? = null) {

        this.mainView = View.inflate(context, R.layout.reactive_search_view_layout, this)
        this.searchField = mainView?.findViewById<View>(R.id.etSearchField) as EditText

        attrs?.let { attrs ->

            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ReactiveSearchView,
                0, 0)
            try {

                a.getString(R.styleable.ReactiveSearchView_searchHint)?.let { searchHint ->

                    searchField.hint = searchHint
                }

            } finally {

                a.recycle()
            }
        }

        searchField.setOnTouchListener { v, event ->

            val DRAWABLE_END = 2

            if (event.action == MotionEvent.ACTION_UP) {

                searchField.compoundDrawablesRelative[DRAWABLE_END]?.let { drawable ->

                    if (event.rawX >= (searchField.right - drawable.bounds.width())) {

                        searchField.text?.clear()
                        searchField.hideKeyboard()
                        onCancelSubject.onNext(Any())
                        true
                    }
                }
            }

            false
        }
    }

    fun getInputListener(): Observable<String> {

        return RxTextView.textChanges(searchField)
                .debounce(SEARCH_TIMER_DELAY_MILLISECONDS, TimeUnit.MILLISECONDS)
                .skip(1)
                .map { it.toString() }
                .distinctUntilChanged()
                .doOnNext(::doOnNext)
                .filter { it.isNotEmpty() }
    }

    private fun doOnNext(text: String) {

        if (text.isEmpty()) {

            searchField.post {

                searchField.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_search_black_24dp, 0, 0, 0)
            }
            onEmptyInputSubject.onNext(Any())

        } else {

            searchField.post {

                searchField.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_search_black_24dp, 0, R.drawable.ic_search_view_cancel, 0)
            }
        }
    }

    fun getEmptyInputListener(): Observable<Any> {

        return onEmptyInputSubject
    }

    fun getCancelListener(): Observable<Any> {

        return onCancelSubject
    }
}