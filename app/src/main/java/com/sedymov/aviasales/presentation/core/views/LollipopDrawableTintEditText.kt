package com.sedymov.aviasales.presentation.core.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.sedymov.aviasales.R
import com.sedymov.aviasales.utils.platform.isLollipop
import com.sedymov.aviasales.utils.platform.tint

class LollipopDrawableTintEditText: AppCompatEditText {

    private var drawableTint: Int? = null

    constructor (context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.editTextStyle)

    constructor (context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        attrs?.let { attrs -> initLollipopDrawableTintEditText(attrs) }
    }

    private fun initLollipopDrawableTintEditText(attrs: AttributeSet) {

        if (isLollipop()) {

            val a = context.theme.obtainStyledAttributes(
                    attrs,
                    R.styleable.LollipopDrawableTintEditText,
                    0, 0)
            try {

                a.getColorStateList(R.styleable.LollipopDrawableTintEditText_drawableTint)?.let { colorStateList ->

                    drawableTint = colorStateList.defaultColor

                    val drawables = compoundDrawablesRelative
                    setCompoundDrawablesRelativeWithIntrinsicBounds(start = drawables[0], top = drawables[1], end = drawables[2], bottom = drawables[3])
                }

            } finally {

                a.recycle()
            }
        }
    }

    override fun setCompoundDrawablesRelativeWithIntrinsicBounds(start: Drawable?, top: Drawable?, end: Drawable?, bottom: Drawable?) {

        drawableTint?.let { drawableTint ->

            val startDrawable = start.tint(drawableTint)
            val topDrawable = top.tint(drawableTint)
            val endDrawable = end.tint(drawableTint)
            val bottomDrawable = bottom.tint(drawableTint)

            super.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, topDrawable, endDrawable, bottomDrawable)

        } ?: super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
    }
}