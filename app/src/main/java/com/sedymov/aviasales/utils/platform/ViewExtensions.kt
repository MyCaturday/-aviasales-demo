package com.sedymov.aviasales.utils.platform

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun View.safeClickListener(): Observable<Any> = RxView.clicks(this).throttleFirst(VIEW_CLICK_DELAY, TimeUnit.MILLISECONDS)

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

inline fun <T : RecyclerView.ViewHolder> ViewGroup.from(layoutRes: Int, factory: (v: View) -> T): T =
        factory(inflate(layoutRes))

fun View.setGone() {
    visibility = View.GONE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setVisible(visible: Boolean) {

    if (visible) setVisible() else setInvisible()
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

// http://www.nasc.fr/android/android-using-layout-as-custom-marker-on-google-map-api/
fun createDrawableFromView(
    context: Context,
    view: View
): Bitmap? {

    val displayMetrics = DisplayMetrics()
    (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    view.layoutParams =
        RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
    view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
    view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
    view.buildDrawingCache()
    val bitmap = Bitmap.createBitmap(
        view.measuredWidth,
        view.measuredHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}

