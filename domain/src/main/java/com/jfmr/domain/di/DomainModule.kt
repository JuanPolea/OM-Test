package com.jfmr.domain.di

import com.jfmr.domain.usecase.RetrieveUnifiedListUseCase
import com.jfmr.domain.usecase.RetrieveUnifiedListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class QRetrieveUnifiedListUseCase


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @InstallIn(SingletonComponent::class)
    @Module
    interface Declarations {

        @Singleton
        @Binds
        @QRetrieveUnifiedListUseCase
        fun bindsRetrieveList(implementation: RetrieveUnifiedListUseCaseImpl): RetrieveUnifiedListUseCase

    }
}
