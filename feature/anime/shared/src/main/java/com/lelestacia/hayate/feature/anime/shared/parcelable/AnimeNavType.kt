package com.lelestacia.hayate.feature.anime.shared.parcelable

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.lelestacia.hayate.common.shared.fromJson
import com.lelestacia.hayate.common.shared.parcelable
import com.lelestacia.hayate.feature.anime.shared.model.Anime

class AnimeNavType : NavType<Anime>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): Anime? {
        return bundle.parcelable(key)
    }

    override fun parseValue(value: String): Anime {
        return fromJson<Anime>(data = Uri.decode(value))
            ?: throw IllegalArgumentException("Failed to Retrieve Anime")
    }

    override fun put(bundle: Bundle, key: String, value: Anime) {
        bundle.putParcelable(key, value)
    }
}