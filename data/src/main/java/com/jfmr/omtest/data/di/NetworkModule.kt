package com.jfmr.omtest.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jfmr.omtest.data.BuildConfig
import com.jfmr.omtest.data.api.rt.v1.RTV1Service
import com.jfmr.omtest.data.api.stv.STVService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class QRTV1Service

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class QSTVService

private const val TIMEOUT: Long = 240

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val timeout: Long = TIMEOUT
        return OkHttpClient()
            .newBuilder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    @QRTV1Service
    fun provideRTV1RetrofitService(client: OkHttpClient, coverter: Gson): RTV1Service {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_RTV1_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(coverter)
            )
            .build().create(RTV1Service::class.java)
    }

    @Provides
    @Singleton
    @QSTVService
    fun provideSTVRetrofitService(client: OkHttpClient, coverter: Gson): STVService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_STV1_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(coverter)
            )
            .build()
            .create(STVService::class.java)
    }


    @Provides
    @Singleton
    fun provideConverter(): Gson =
        GsonBuilder()
            .setLenient()
            .create()


}
