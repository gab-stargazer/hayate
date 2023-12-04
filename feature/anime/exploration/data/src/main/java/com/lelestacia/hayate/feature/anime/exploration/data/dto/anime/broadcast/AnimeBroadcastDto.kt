package com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.broadcast


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeBroadcastDto(

    @Json(name = "day")
    val day: String?,

    @Json(name = "time")
    val time: String?,

    @Json(name = "timezone")
    val timezone: String?,

    @Json(name = "string")
    val string: String?
)