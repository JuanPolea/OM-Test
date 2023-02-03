package com.jfmr.omtest.data.di

import javax.inject.Qualifier

object Dispatchers {

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class DispatcherIO
}
