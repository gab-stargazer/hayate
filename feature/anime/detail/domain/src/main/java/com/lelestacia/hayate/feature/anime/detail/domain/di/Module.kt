package com.lelestacia.hayate.feature.anime.detail.domain.di

import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import com.lelestacia.hayate.feature.anime.detail.domain.usecases.DetailAnimeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object Module {

    @Provides
    @ViewModelScoped
    fun provideUseCases(
        repository: AnimeRepository,
    ): DetailAnimeUseCases {
        return DetailAnimeUseCases(repository)
    }
}