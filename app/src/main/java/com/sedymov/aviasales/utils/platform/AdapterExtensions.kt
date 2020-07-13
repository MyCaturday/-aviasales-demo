package com.sedymov.aviasales.utils.platform

import androidx.recyclerview.widget.RecyclerView

fun <VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.setAsAdapterFor(recyclerView: RecyclerView) {

    recyclerView.adapter = this
}