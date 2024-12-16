package com.example.ptiapplicationv2.di.network

import com.example.ptiapplicationv2.di.network.adapters.SagencyResultJsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val TIMEOUT = 15L
private const val PTI_BASE_URL = "https://epti.ge/api/deadlines/pti/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).callTimeout(TIMEOUT, TimeUnit.SECONDS).writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS).connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true).build()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder().baseUrl(PTI_BASE_URL).addConverterFactory(
        MoshiConverterFactory.create(
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .add(SagencyResultJsonAdapter())
                .build()
        )
    ).client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideRetrofitCurrency(retrofitClient: Retrofit): PtiService =
        retrofitClient.create(PtiService::class.java)

}