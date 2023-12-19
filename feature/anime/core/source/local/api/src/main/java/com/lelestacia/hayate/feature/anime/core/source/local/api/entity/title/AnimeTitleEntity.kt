package com.lelestacia.hayate.feature.anime.core.source.local.api.entity.title

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeTitleEntity(

    @Json(name = "type")
    val type: String,

    @Json(name = "title")
    val title: String
)
