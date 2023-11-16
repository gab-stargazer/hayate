package com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.title


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeTitleDto(

    @Json(name = "type")
    val type: String,

    @Json(name = "title")
    val title: String
)