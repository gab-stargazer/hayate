package com.lelestacia.hayate.feature.anime.core.common.filter

/**
 * Enumeration representing different ratings for anime content.
 * Each rating includes a title describing the content suitability and
 * a corresponding query string often used for classification purposes.
 *
 * The `@Suppress("unused")` annotation is applied because some IDEs or code inspection tools
 * might incorrectly identify enum members as unused, particularly if used indirectly,
 * potentially generating unnecessary warnings or errors.
 */
@Suppress("unused")
enum class AnimeRating(val title: String, val query: String) {
    G("G - All Ages", "g"),
    PG("PG - Children", "pg"),
    PG_13("PG-13 - Teens 13 or older", "pg13"),
    R17("R - 17+ (Violence & profanity)", "r17"),
    R("R+ - Mild Nudity", "r")
}