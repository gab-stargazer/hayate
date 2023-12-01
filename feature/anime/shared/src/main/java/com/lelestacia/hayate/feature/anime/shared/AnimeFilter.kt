package com.lelestacia.hayate.feature.anime.shared

enum class AnimeFilter(
    val title: String,
    val query: String
) {
    AIRING("Airing", "airing"),
    UPCOMING("Upcoming", "upcoming"),
    BY_POPULARITY("By Popularity", "bypopularity"),
    FAVORITE("Favorite", "favorite")
}