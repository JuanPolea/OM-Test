package com.jfmr.domain.di

import com.jfmr.domain.usecase.detail.DetailUseCase
import com.jfmr.domain.usecase.detail.DetailUseCaseImpl
import com.jfmr.domain.usecase.unifiedList.RetrieveUnifiedListUseCase
import com.jfmr.domain.usecase.unifiedList.RetrieveUnifiedListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class QRetrieveUnifiedListUseCase


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class QDetailUseCase


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


        @Singleton
        @Binds
        @QDetailUseCase
        fun bindsDetail(implementation: DetailUseCaseImpl): DetailUseCase

    }
}
