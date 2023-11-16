package com.lelestacia.hayate.feature.anime.exploration.domain.model.trailer

data class AnimeTrailer(
    val youtubeId: String?,
    val url: String?,
    val embedUrl: String?,
    val images: AnimeTrailerImages
)
