package com.lelestacia.hayate.feature.anime.core.source.remote.api

import androidx.paging.PagingSource
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto

interface AnimeRemoteDataSourceApi {

    fun getTopAnime(
        type: String? = null,
        filter: String? = null,
        rating: String? = null,
        sfw: Boolean = true,
    ): PagingSource<Int, AnimeDto>

    fun getCurrentSeasonAnime(
        filter: String? = null,
        sfw: Boolean = true,
    ): PagingSource<Int, AnimeDto>

    fun getUpcomingSeasonAnime(
        filter: String? = null,
        sfw: Boolean = true,
    ): PagingSource<Int, AnimeDto>

    fun getScheduledAnime(
        filter: String? = null,
        sfw: Boolean = true,
    ): PagingSource<Int, AnimeDto>
}