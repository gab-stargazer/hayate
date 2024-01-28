package com.lelestacia.hayate.feature.anime.core.source.local.api.entity.aired

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeAiredEntity(

    @Json(name = "from")
    val from: String?,

    @Json(name = "to")
    val to: String?,

    @Json(name = "prop")
    val prop: AnimePropEntity,

    @Json(name = "string")
    val string: String
)
