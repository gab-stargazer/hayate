package com.lelestacia.hayate.feature.anime.core.source.remote.impl.di

import com.lelestacia.hayate.core.common.util.IoDispatcher
import com.lelestacia.hayate.feature.anime.core.source.remote.api.api.AnimeRemoteDataSourceApi
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.ScheduleEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.SearchEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.SeasonEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.TopEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.UtilityEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.source.AnimeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
internal object SourceModule {

    @Provides
    @Singleton
    fun provideAnimeDataSource(
        topEndpoint: TopEndpoint,
        seasonEndpoint: SeasonEndpoint,
        scheduleEndpoint: ScheduleEndpoint,
        utilityEndpoint: UtilityEndpoint,
        searchEndpoint: SearchEndpoint,
        @IoDispatcher dispatcher: CoroutineContext,
    ): AnimeRemoteDataSourceApi {
        return AnimeRemoteDataSourceImpl(
            topEndpoint = topEndpoint,
            seasonEndpoint = seasonEndpoint,
            scheduleEndpoint = scheduleEndpoint,
            utilityEndpoint = utilityEndpoint,
            searchEndpoint = searchEndpoint,
            ioDispatcher = dispatcher
        )
    }
}