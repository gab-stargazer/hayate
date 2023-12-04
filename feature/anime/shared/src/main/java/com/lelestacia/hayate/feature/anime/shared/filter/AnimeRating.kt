package com.lelestacia.hayate.feature.anime.shared.filter

@Suppress("unused")
enum class AnimeRating(val title: String, val query: String) {
    G("G - All Ages", "g"),
    PG("PG - Children", "pg"),
    PG_13("PG-13 - Teens 13 or older", "pg13"),
    R17("R - 17+ (Violence & profanity)", "r17"),
    R("R+ - Mild Nudity", "r")
}