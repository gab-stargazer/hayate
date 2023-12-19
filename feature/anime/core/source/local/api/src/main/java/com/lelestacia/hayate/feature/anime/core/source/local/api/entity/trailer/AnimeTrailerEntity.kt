package com.lelestacia.hayate.feature.anime.core.source.local.api.entity.trailer

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeTrailerEntity(

    @Json(name = "youtube_id")
    val youtubeId: String?,

    @Json(name = "url")
    val url: String?,

    @Json(name = "embed_url")
    val embedUrl: String?,

    @Json(name = "images")
    val images: AnimeTrailerImagesEntity
)
