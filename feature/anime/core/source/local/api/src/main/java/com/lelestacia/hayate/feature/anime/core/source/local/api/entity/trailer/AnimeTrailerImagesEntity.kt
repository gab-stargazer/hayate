package com.lelestacia.hayate.feature.anime.core.source.local.api.entity.trailer

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeTrailerImagesEntity(

    @Json(name = "image_url")
    val imageUrl: String?,

    @Json(name = "small_image_url")
    val smallImageUrl: String?,

    @Json(name = "medium_image_url")
    val mediumImageUrl: String?,

    @Json(name = "large_image_url")
    val largeImageUrl: String?,

    @Json(name = "maximum_image_url")
    val maximumImageUrl: String?
)
