package com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.pagination


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimePaginationDto(

    @Json(name = "last_visible_page")
    val lastVisiblePage: Int,

    @Json(name = "has_next_page")
    val hasNextPage: Boolean,

    @Json(name = "current_page")
    val currentPage: Int,

    @Json(name = "items")
    val items: AnimePaginationItemsDto
)