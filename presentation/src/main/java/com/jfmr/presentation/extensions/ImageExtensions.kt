package com.jfmr.presentation.extensions

import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadImage(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .into(this)
}

fun ImageView.loadGif(rawResource: Int) {
    Glide
        .with(this.context)
        .asGif()
        .load(rawResource)
        .into(this)
}

fun ImageView.clear(fragment: Fragment) =
    Glide.with(fragment).clear(this)
