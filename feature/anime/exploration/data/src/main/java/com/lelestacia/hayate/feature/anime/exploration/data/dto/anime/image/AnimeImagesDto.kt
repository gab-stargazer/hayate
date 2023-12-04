package com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.image


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeImagesDto(

    @Json(name = "jpg")
    val jpg: AnimeJpgDto,

    @Json(name = "webp")
    val webp: AnimeWebpDto
)