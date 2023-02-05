package com.jfmr.presentation.extensions

import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
fun ImageView.loadGif(rawResource: Int) {
    Glide
        .with(this.context)
        .asGif()
        .load(rawResource)
        .into(this)
}

fun ImageView.clear(fragment: Fragment) =
    Glide.with(fragment).clear(this)
