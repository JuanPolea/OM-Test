package com.jfmr.presentation.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun ImageView.loadImage(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .transition(withCrossFade())
        .into(this)
}
