package com.lelestacia.hayate.feature.anime.exploration.domain.di

import com.lelestacia.hayate.common.preferences.ConfigPreferences
import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
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
    fun provideAnimeUseCases(
        repository: AnimeRepository,
        preferences: ConfigPreferences
    ): AnimeUseCases {
        return AnimeUseCases(
            repository = repository,
            preferences = preferences
        )
    }
}