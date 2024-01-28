package com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.demographic


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeDemographicDto(

    @Json(name = "mal_id")
    val malId: Int,

    @Json(name = "type")
    val type: String?,

    @Json(name = "name")
    val name: String,

    @Json(name = "url")
    val url: String
)