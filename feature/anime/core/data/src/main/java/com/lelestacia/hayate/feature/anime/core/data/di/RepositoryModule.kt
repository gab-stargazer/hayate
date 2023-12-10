package com.lelestacia.hayate.feature.anime.core.data.di

import com.lelestacia.hayate.feature.anime.core.data.AnimeRepositoryImpl
import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import com.lelestacia.hayate.feature.anime.core.source.remote.api.AnimeRemoteDataSourceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    @Singleton
    fun provideAnimeRepository(
        animeRemoteDataSource: AnimeRemoteDataSourceApi
    ): AnimeRepository {
        return AnimeRepositoryImpl(
            remoteDataSource = animeRemoteDataSource
        )
    }
}