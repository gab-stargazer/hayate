package com.lelestacia.hayate.feature.anime.core.source.remote.impl.source

import androidx.paging.PagingSource
import com.lelestacia.hayate.core.common.api.LoggerApi
import com.lelestacia.hayate.core.common.util.IoDispatcher
import com.lelestacia.hayate.feature.anime.core.source.remote.api.api.AnimeRemoteDataSourceApi
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.demographic.AnimeDemographicDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.genre.AnimeGenreDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.theme.AnimeThemeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.ScheduleEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.SearchEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.SeasonEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.TopEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.UtilityEndpoint
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class AnimeRemoteDataSourceImpl @Inject constructor(
    private val topEndpoint: TopEndpoint,
    private val seasonEndpoint: SeasonEndpoint,
    private val scheduleEndpoint: ScheduleEndpoint,
    private val utilityEndpoint: UtilityEndpoint,
    private val searchEndpoint: SearchEndpoint,
    private val loggerApi: LoggerApi,
    @IoDispatcher private val ioDispatcher: CoroutineContext,
) : AnimeRemoteDataSourceApi {

    override fun getTopAnime(
        type: String?,
        filter: String?,
        rating: String?,
        sfw: Boolean,
    ): PagingSource<Int, AnimeDto> {
        return with(loggerApi) {
            TopAnimePaging(
                topEndpoint = topEndpoint,
                type = type,
                filter = filter,
                rating = rating,
                sfw = sfw,
                ioDispatcher = ioDispatcher
            )
        }
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
        sfw: Boolean,
    ): PagingSource<Int, AnimeDto> {
        return UpcomingSeasonAnimePaging(
            seasonEndpoint = seasonEndpoint,
            filter = filter,
            sfw = sfw
        )
    }

    override fun getScheduledAnime(
        filter: String?,
        sfw: Boolean,
    ): PagingSource<Int, AnimeDto> {
        return ScheduleAnimePaging(
            scheduleEndpoint = scheduleEndpoint,
            filter = filter,
            sfw = sfw
        )
    }

    override fun getAnimeSearch(
        query: String,
        type: String?,
        rating: String?,
        sfw: Boolean,
    ): PagingSource<Int, AnimeDto> {
        return SearchAnimePaging(
            endpoint = searchEndpoint,
            query = query,
            type = type,
            rating = rating,
            sfw = sfw
        )
    }

    override suspend fun getGenres(): List<AnimeGenreDto> {
        return withContext(ioDispatcher) {
            utilityEndpoint.getGenres().data
        }
    }

    override suspend fun getExplicitGenres(): List<AnimeGenreDto> {
        return withContext(ioDispatcher) {
            utilityEndpoint.getExplicitGenres().data
        }
    }

    override suspend fun getThemes(): List<AnimeThemeDto> {
        return withContext(ioDispatcher) {
            utilityEndpoint.getThemes().data
        }
    }

    override suspend fun getDemographics(): List<AnimeDemographicDto> {
        return withContext(ioDispatcher) {
            utilityEndpoint.getDemographics().data
        }
    }
}