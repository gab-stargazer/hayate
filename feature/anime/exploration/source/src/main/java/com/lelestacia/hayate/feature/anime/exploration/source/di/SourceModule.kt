package com.lelestacia.hayate.feature.anime.exploration.source.di

import com.lelestacia.hayate.feature.anime.exploration.data.source.AnimeRemoteDataSource
import com.lelestacia.hayate.feature.anime.exploration.source.endpoint.ScheduleEndpoint
import com.lelestacia.hayate.feature.anime.exploration.source.endpoint.SeasonEndpoint
import com.lelestacia.hayate.feature.anime.exploration.source.endpoint.TopEndpoint
import com.lelestacia.hayate.feature.anime.exploration.source.source.AnimeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {

    @Provides
    @Singleton
    fun provideAnimeDataSource(
        topEndpoint: TopEndpoint,
        seasonEndpoint: SeasonEndpoint,
        scheduleEndpoint: ScheduleEndpoint
    ): AnimeRemoteDataSource {
        return AnimeRemoteDataSourceImpl(
            topEndpoint = topEndpoint,
            seasonEndpoint = seasonEndpoint,
            scheduleEndpoint = scheduleEndpoint
        )
    }
}