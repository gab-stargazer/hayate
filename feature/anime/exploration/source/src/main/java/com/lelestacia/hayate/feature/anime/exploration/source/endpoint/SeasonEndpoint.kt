package com.lelestacia.hayate.feature.anime.exploration.source.endpoint

import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.pagination.PaginationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SeasonEndpoint {

    @GET("seasons/now")
    suspend fun getCurrentSeasonAnime(
        @Query("filter") filter: String? = null,
        @Query("sfw") sfw: Boolean? = true,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): PaginationDto<AnimeDto>

    @GET("seasons/upcoming")
    suspend fun getUpcomingSeasonAnime(
        @Query("filter") filter: String? = null,
        @Query("sfw") sfw: Boolean? = true,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): PaginationDto<AnimeDto>
}