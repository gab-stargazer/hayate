package com.lelestacia.hayate.feature.anime.search.domain.di

import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import com.lelestacia.hayate.feature.anime.search.domain.usecases.SearchUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object InternalModule {

    @Provides
    @ViewModelScoped
    fun provideUseCases(
        repository: AnimeRepository,
    ): SearchUseCases {
        return SearchUseCases(repository)
    }
}