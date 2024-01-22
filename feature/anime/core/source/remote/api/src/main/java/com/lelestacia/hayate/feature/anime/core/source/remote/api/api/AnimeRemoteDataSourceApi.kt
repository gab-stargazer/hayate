package com.lelestacia.hayate.feature.anime.core.source.remote.api.api

import androidx.paging.PagingSource
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.demographic.AnimeDemographicDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.genre.AnimeGenreDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.theme.AnimeThemeDto

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

    fun getAnimeSearch(
        query: String = "",
        type: String? = null,
        rating: String? = null,
        sfw: Boolean = true,
    ): PagingSource<Int, AnimeDto>

    suspend fun getGenres(): List<AnimeGenreDto>

    suspend fun getExplicitGenres(): List<AnimeGenreDto>

    suspend fun getThemes(): List<AnimeThemeDto>

    suspend fun getDemographics(): List<AnimeDemographicDto>
}