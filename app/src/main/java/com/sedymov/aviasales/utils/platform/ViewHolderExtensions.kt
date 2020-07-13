package com.sedymov.aviasales.utils.platform

import androidx.recyclerview.widget.RecyclerView

inline fun RecyclerView.ViewHolder.checkPositionAndRun(r: (position: Int) -> Unit) {

    adapterPosition.let { pos ->

        if (pos != RecyclerView.NO_POSITION) {

            r.invoke(pos)
        }
    }
}