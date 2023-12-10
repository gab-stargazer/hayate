package com.lelestacia.hayate.feature.anime.core.common.filter

/**
 * Enumerates various filters for anime content.
 * Each filter option contains a title describing its purpose and
 * a corresponding query string often used for classification or sorting.
 *
 * The `@Suppress("unused")` annotation is applied because some IDEs or code inspection tools
 * might erroneously identify enum members as unused, particularly if they're indirectly used,
 * potentially generating unwarranted warnings or errors.
 */
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