package com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint

import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.pagination.PaginationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchEndpoint {

    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") query: String,
        @Query("type") type: String? = null,
        @Query("rating") rating: String? = null,
        @Query("sfw") sfw: Boolean? = true,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): PaginationDto<AnimeDto>
}