package com.lelestacia.hayate.feature.anime.initialization.domain.di

import com.lelestacia.hayate.core.preferences.ConfigPreferences
import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import com.lelestacia.hayate.feature.anime.initialization.domain.usecases.InitializationUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideInitializationUseCases(
        configPreferences: ConfigPreferences,
        animeRepository: AnimeRepository
    ): InitializationUseCases {
        return InitializationUseCases(
            animeRepository = animeRepository,
            configPreferences = configPreferences
        )
    }
}