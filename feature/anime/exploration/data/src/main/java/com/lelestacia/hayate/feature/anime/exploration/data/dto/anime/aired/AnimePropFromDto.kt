package com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.aired


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimePropFromDto(

    @Json(name = "day")
    val day: Int?,

    @Json(name = "month")
    val month: Int?,

    @Json(name = "year")
    val year: Int?
)