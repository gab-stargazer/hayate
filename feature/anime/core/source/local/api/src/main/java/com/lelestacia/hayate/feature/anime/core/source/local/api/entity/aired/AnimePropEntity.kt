package com.lelestacia.hayate.feature.anime.core.source.local.api.entity.aired

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimePropEntity(

    @Json(name = "from")
    val from: AnimePropFromEntity,

    @Json(name = "to")
    val to: AnimePropToEntity
)
