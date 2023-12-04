package com.lelestacia.hayate.di

import com.lelestacia.hayate.feature.anime.exploration.ui.di.ExploreModuleAPI
import com.lelestacia.hayate.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideNavigationProvider(
        module: ExploreModuleAPI
    ): NavigationProvider {
        return NavigationProvider(module)
    }
}