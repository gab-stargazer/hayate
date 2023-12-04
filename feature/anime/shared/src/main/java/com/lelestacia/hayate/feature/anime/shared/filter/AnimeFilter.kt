package com.lelestacia.hayate.feature.anime.shared.filter

@Suppress("unused")
enum class AnimeFilter(
    val title: String,
    val query: String
) {
    AIRING("Airing", "airing"),
    UPCOMING("Upcoming", "upcoming"),
    BY_POPULARITY("By Popularity", "bypopularity"),
    FAVORITE("Favorite", "favorite")
}