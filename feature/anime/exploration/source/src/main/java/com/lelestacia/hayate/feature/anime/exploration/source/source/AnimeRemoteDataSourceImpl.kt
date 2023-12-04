package com.lelestacia.hayate.feature.anime.exploration.source.source

import androidx.paging.PagingSource
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.exploration.data.source.AnimeRemoteDataSource
import com.lelestacia.hayate.feature.anime.exploration.source.endpoint.ScheduleEndpoint
import com.lelestacia.hayate.feature.anime.exploration.source.endpoint.SeasonEndpoint
import com.lelestacia.hayate.feature.anime.exploration.source.endpoint.TopEndpoint
import javax.inject.Inject

class AnimeRemoteDataSourceImpl @Inject constructor(
    private val topEndpoint: TopEndpoint,
    private val seasonEndpoint: SeasonEndpoint,
    private val scheduleEndpoint: ScheduleEndpoint
) : AnimeRemoteDataSource {

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