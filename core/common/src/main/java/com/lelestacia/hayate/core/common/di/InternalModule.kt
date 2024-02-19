package com.lelestacia.hayate.core.common.di

import com.lelestacia.hayate.core.common.api.LoggerApi
import com.lelestacia.hayate.core.common.util.LoggerApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InternalModule {

    @Provides
    @Singleton
    fun provideLoggerAPI(): LoggerApi {
        return LoggerApiImpl()
    }
}