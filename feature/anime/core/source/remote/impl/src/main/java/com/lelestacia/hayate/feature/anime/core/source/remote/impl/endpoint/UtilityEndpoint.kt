package com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint

import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.DataContainer
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.demographic.AnimeDemographicDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.genre.AnimeGenreDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.theme.AnimeThemeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.util.GenreEnum
import retrofit2.http.GET
import retrofit2.http.Query

interface UtilityEndpoint {

    @GET("genres/anime")
    suspend fun getGenres(
        @Query("filter") filter: String = GenreEnum.GENRES.name.lowercase()
    ): DataContainer<List<AnimeGenreDto>>

    @GET("genres/anime")
    suspend fun getExplicitGenres(
        @Query("filter") filter: String = GenreEnum.EXPLICIT_GENRES.name.lowercase()
    ): DataContainer<List<AnimeGenreDto>>

    @GET("genres/anime")
    suspend fun getThemes(
        @Query("filter") filter: String = GenreEnum.THEMES.name.lowercase()
    ): DataContainer<List<AnimeThemeDto>>

    @GET("genres/anime")
    suspend fun getDemographics(
        @Query("filter") filter: String = GenreEnum.DEMOGRAPHICS.name.lowercase()
    ): DataContainer<List<AnimeDemographicDto>>
}