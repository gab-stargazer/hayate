package com.lelestacia.hayate.feature.anime.core.source.remote.impl.source

import androidx.paging.PagingSource
import com.lelestacia.hayate.feature.anime.core.source.remote.api.AnimeRemoteDataSourceApi
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.ScheduleEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.SeasonEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.TopEndpoint
import javax.inject.Inject

internal class AnimeRemoteDataSourceImpl @Inject constructor(
    private val topEndpoint: TopEndpoint,
    private val seasonEndpoint: SeasonEndpoint,
    private val scheduleEndpoint: ScheduleEndpoint
) : AnimeRemoteDataSourceApi {

    override fun getTopAnime(
        type: String?,
        filter: String?,
        rating: String?,
        sfw: Boolean
    ): PagingSource<Int, AnimeDto> {
        return TopAnimePaging(
            topEndpoint = topEndpoint,
            type = type,
            filter = filter,
            rating = rating,
            sfw = sfw
        )
    }

    override fun getCurrentSeasonAnime(
        filter: String?,
        sfw: Boolean,
    ): PagingSource<Int, AnimeDto> {
        return CurrentSeasonAnimePaging(
            seasonEndpoint = seasonEndpoint,
            filter = filter,
            sfw = sfw
        )
    }

    override fun getUpcomingSeasonAnime(
        filter: String?,
        sfw: Boolean
    ): PagingSource<Int, AnimeDto> {
        return UpcomingSeasonAnimePaging(
            seasonEndpoint = seasonEndpoint,
            filter = filter,
            sfw = sfw
        )
    }

    override fun getScheduledAnime(
        filter: String?,
        sfw: Boolean
    ): PagingSource<Int, AnimeDto> {
        return ScheduleAnimePaging(
            scheduleEndpoint = scheduleEndpoint,
            filter = filter,
            sfw = sfw
        )
    }
}