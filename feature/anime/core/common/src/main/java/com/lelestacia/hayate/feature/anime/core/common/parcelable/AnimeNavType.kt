package com.lelestacia.hayate.feature.anime.core.common.parcelable

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.lelestacia.hayate.common.shared.util.fromJson
import com.lelestacia.hayate.common.shared.util.parcelable
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime

/**
 * Custom [NavType] implementation specifically designed for handling navigation
 * of [Anime] objects within a [Bundle].
 *
 * @property isNullableAllowed Indicates whether null values are allowed or not. Defaults to false.
 */
class AnimeNavType : NavType<Anime>(isNullableAllowed = false) {

    /**
     * Retrieves an [Anime] object from the provided [Bundle] using the given [key].
     *
     * @param bundle The [Bundle] containing the [Anime] object.
     * @param key The identifier for retrieving the [Anime] object from the [Bundle].
     * @return The retrieved [Anime] object, or null if not found.
     */
    override fun get(bundle: Bundle, key: String): Anime? {
        return bundle.parcelable(key)
    }

    /**
     * Parses the provided [value] string into an [Anime] object.
     * Decodes the URI-encoded [value] as JSON to retrieve the [Anime] object.
     *
     * @param value The JSON encoded string representing the [Anime] object.
     * @return The parsed [Anime] object.
     * @throws IllegalArgumentException if parsing fails or the [Anime] object cannot be retrieved.
     */
    override fun parseValue(value: String): Anime {
        return fromJson<Anime>(data = Uri.decode(value))
            ?: throw IllegalArgumentException("Failed to Retrieve Anime")
    }

    /**
     * Puts an [Anime] object into the provided [Bundle] using the given [key].
     *
     * @param bundle The [Bundle] to store the [Anime] object.
     * @param key The identifier for storing the [Anime] object in the [Bundle].
     * @param value The [Anime] object to be stored.
     */
    override fun put(bundle: Bundle, key: String, value: Anime) {
        bundle.putParcelable(key, value)
    }
}