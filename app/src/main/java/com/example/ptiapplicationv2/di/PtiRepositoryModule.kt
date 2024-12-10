package com.example.ptiapplicationv2.di

import com.example.ptiapplicationv2.data.repository.PtiRepositoryImpl
import com.example.ptiapplicationv2.domain.PtiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PtiRepositoryModule {

    @Binds
    abstract fun bindAnalyticsService(
        analyticsServiceImpl: PtiRepositoryImpl
    ): PtiRepository
}