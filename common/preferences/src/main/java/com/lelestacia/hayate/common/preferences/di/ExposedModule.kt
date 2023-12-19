package com.lelestacia.hayate.common.preferences.di

import android.content.Context
import com.lelestacia.hayate.common.preferences.ConfigPreferences
import com.lelestacia.hayate.common.preferences.ConfigPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExposedModule {

    @Provides
    @Singleton
    fun provideConfigPreferences(
        @ApplicationContext context: Context
    ): ConfigPreferences {
        return ConfigPreferencesImpl(context)
    }
}