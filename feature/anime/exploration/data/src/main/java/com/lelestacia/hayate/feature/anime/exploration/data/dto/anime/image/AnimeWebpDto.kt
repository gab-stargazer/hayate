package com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.image


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeWebpDto(

    @Json(name = "image_url")
    val imageUrl: String,

    @Json(name = "small_image_url")
    val smallImageUrl: String,

    @Json(name = "large_image_url")
    val largeImageUrl: String
)