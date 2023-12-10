package com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.aired


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimePropToDto(

    @Json(name = "day")
    val day: Int?,

    @Json(name = "month")
    val month: Int?,

    @Json(name = "year")
    val year: Int?
)