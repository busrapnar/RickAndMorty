package com.busrapinar.rickandmorty2.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(s: String) {
    Glide.with(this.context).load(s).into(this)
}