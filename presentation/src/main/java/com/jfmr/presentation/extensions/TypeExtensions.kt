package com.jfmr.presentation.extensions

object TypeExtensions {

    fun String?.orDefault() =
        if (this.isNullOrEmpty()) {
            "-"
        } else {
            this
        }

}
