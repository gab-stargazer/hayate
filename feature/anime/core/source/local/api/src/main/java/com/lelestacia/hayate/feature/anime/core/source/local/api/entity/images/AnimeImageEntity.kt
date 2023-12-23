package com.lelestacia.hayate.feature.anime.core.source.local.api.entity.images

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeImageEntity(

    @Json(name = "jpeg")
    val jpg: AnimeJpegEntity,

    @Json(name = "webp")
    val webp: AnimeWebpEntity
)
