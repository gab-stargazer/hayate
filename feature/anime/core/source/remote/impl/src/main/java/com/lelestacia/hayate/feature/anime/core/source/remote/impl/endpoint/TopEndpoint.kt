package com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint

import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.pagination.PaginationDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface TopEndpoint {

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("type") type: String? = null,
        @Query("filter") filter: String? = null,
        @Query("rating") rating: String? = null,
        @Query("sfw") sfw: Boolean? = true,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): PaginationDto<AnimeDto>
}