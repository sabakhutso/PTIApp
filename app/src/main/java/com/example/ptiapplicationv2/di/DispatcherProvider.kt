package com.example.ptiapplicationv2.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatcherProvider {

    fun io(): CoroutineDispatcher

    fun ui(): CoroutineDispatcher

    fun uiImmediate(): CoroutineDispatcher

    fun default(): CoroutineDispatcher

    class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {
        override fun io(): CoroutineDispatcher = Dispatchers.IO
        override fun ui() = Dispatchers.Main
        override fun uiImmediate() = Dispatchers.Main.immediate
        override fun default() = Dispatchers.Default
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DispatcherModule {

        @Binds
        fun provideDispatchers(dispatcherProvider: DispatcherProviderImpl): DispatcherProvider
    }
}
