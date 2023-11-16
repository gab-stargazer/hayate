package com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.pagination


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimePaginationItemsDto(

    @Json(name = "count")
    val count: Int,

    @Json(name = "total")
    val total: Int,

    @Json(name = "per_page")
    val perPage: Int
)