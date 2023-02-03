package com.jfmr.omtest.data.di

import com.jfmr.domain.repository.UnifiedListRepository
import com.jfmr.omtest.data.remote.UnifiedListRemoteDataSource
import com.jfmr.omtest.data.remote.UnifiedListRemoteDataSourceImpl
import com.jfmr.omtest.data.repository.UnifiedListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsUnifiedList(implementation: UnifiedListRepositoryImpl): UnifiedListRepository

    @Singleton
    @Binds
    fun bindsUnifiedListRemoteDS(implementation: UnifiedListRemoteDataSourceImpl): UnifiedListRemoteDataSource

    @Module
    @InstallIn(SingletonComponent::class)
    object Providers {

        @Provides
        @Singleton
        @com.jfmr.omtest.data.di.Dispatchers.DispatcherIO
        internal fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
    }

}
