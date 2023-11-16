package com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.aired


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimePropDto(

    @Json(name = "from")
    val from: AnimePropFromDto,

    @Json(name = "to")
    val to: AnimePropToDto
)