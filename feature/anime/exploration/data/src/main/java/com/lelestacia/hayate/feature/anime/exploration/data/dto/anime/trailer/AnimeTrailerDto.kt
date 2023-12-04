package com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.trailer


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeTrailerDto(

    @Json(name = "youtube_id")
    val youtubeId: String?,

    @Json(name = "url")
    val url: String?,

    @Json(name = "embed_url")
    val embedUrl: String?,

    @Json(name = "images")
    val images: AnimeTrailerImagesDto
)