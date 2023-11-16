package com.lelestacia.hayate.feature.anime.exploration.data.dto.pagination


import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.pagination.AnimePaginationDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationDto<out T>(

    @Json(name = "pagination")
    val pagination: AnimePaginationDto,

    @Json(name = "data")
    val `data`: List<T>
)