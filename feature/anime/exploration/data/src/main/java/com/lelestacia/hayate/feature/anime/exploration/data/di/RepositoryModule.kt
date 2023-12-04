package com.lelestacia.hayate.feature.anime.exploration.data.di

import com.lelestacia.hayate.feature.anime.exploration.data.repository.AnimeRepositoryImpl
import com.lelestacia.hayate.feature.anime.exploration.data.source.AnimeRemoteDataSource
import com.lelestacia.hayate.feature.anime.exploration.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAnimeRepository(
        animeRemoteDataSource: AnimeRemoteDataSource
    ): AnimeRepository {
        return AnimeRepositoryImpl(
            remoteDataSource = animeRemoteDataSource
        )
    }
}