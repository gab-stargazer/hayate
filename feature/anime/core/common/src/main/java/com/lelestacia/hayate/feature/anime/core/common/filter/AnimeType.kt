package com.lelestacia.hayate.feature.anime.core.common.filter

/**
 * Enumeration representing different types of anime.
 * Includes classifications such as TV series, movies, OVAs (Original Video Animations),
 * specials, ONAs (Original Net Animations), and music-related anime.
 *
 * The `@Suppress("unused")` annotation is added because some IDEs or code inspection tools
 * may incorrectly identify enum members as unused when they are used indirectly,
 * leading to unnecessary warnings or errors.
 */
@Suppress("unused")
enum class AnimeType {
    Tv, Movie, OVA, Special, ONA, Music
}