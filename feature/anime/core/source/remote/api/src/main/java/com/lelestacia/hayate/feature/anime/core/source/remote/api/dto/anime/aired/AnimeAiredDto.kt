package com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.aired


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeAiredDto(

    @Json(name = "from")
    val from: String?,

    @Json(name = "to")
    val to: String?,

    @Json(name = "prop")
    val prop: AnimePropDto,

    @Json(name = "string")
    val string: String
)