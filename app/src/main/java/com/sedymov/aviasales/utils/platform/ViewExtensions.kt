package com.sedymov.aviasales.utils.platform

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView


fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

inline fun <T : RecyclerView.ViewHolder> ViewGroup.from(layoutRes: Int, factory: (v: View) -> T): T =
        factory(inflate(layoutRes))

fun View.setGone() {
    visibility = View.GONE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.hideKeyboard() {

    post {

        val inputManager =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

        clearFocus()
        inputManager?.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun Drawable?.tint(tint: Int, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_ATOP): Drawable? {

    return this?.let { drawable ->

        DrawableCompat.wrap(drawable).apply {

            DrawableCompat.setTint(this, tint)
            DrawableCompat.setTintMode(this, mode)
        }
    }
}
