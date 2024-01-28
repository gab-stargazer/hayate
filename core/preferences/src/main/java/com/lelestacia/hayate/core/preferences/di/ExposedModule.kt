package com.lelestacia.hayate.core.preferences.di

import android.content.Context
import com.lelestacia.hayate.core.preferences.ConfigPreferences
import com.lelestacia.hayate.core.preferences.ConfigPreferencesImpl
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
        @ApplicationContext context: Context,
    ): ConfigPreferences {
        return ConfigPreferencesImpl(context)
    }
}