package com.jfmr.omtest.data.di

import com.jfmr.domain.repository.detail.DetailRepository
import com.jfmr.domain.repository.recommendations.RecommendationsRepository
import com.jfmr.domain.repository.unifiedList.UnifiedListRepository
import com.jfmr.omtest.data.remote.detail.DetailRemoteDataSource
import com.jfmr.omtest.data.remote.detail.DetailRemoteDataSourceImpl
import com.jfmr.omtest.data.remote.recommendations.RecommendationsDataSource
import com.jfmr.omtest.data.remote.recommendations.RecommendationsDataSourceImpl
import com.jfmr.omtest.data.remote.unifiedList.UnifiedListRemoteDataSource
import com.jfmr.omtest.data.remote.unifiedList.UnifiedListRemoteDataSourceImpl
import com.jfmr.omtest.data.repository.detail.DetailRepositoryImpl
import com.jfmr.omtest.data.repository.recommendations.RecommendationsRepositoryImpl
import com.jfmr.omtest.data.repository.unifiedList.UnifiedListRepositoryImpl
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

    @Singleton
    @Binds
    fun bindsUDetailRepository(implementation: DetailRepositoryImpl): DetailRepository

    @Singleton
    @Binds
    fun bindsDetailRemoteDataSource(implementation: DetailRemoteDataSourceImpl): DetailRemoteDataSource

    @Singleton
    @Binds
    fun bindsRecommendationsRepository(implementation: RecommendationsRepositoryImpl): RecommendationsRepository

   @Singleton
    @Binds
    fun bindsRecommendationsDataSource(implementation: RecommendationsDataSourceImpl): RecommendationsDataSource

    @Module
    @InstallIn(SingletonComponent::class)
    object Providers {

        @Provides
        @Singleton
        @com.jfmr.omtest.data.di.Dispatchers.DispatcherIO
        internal fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
    }

}
