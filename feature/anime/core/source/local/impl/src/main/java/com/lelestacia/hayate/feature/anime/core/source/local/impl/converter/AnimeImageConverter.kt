package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import android.net.Uri
import androidx.room.TypeConverter
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.images.AnimeImagesEntity

internal class AnimeImageConverter {

    @TypeConverter
    fun fromJson(json: String): AnimeImagesEntity {
        return fromJson(Uri.decode(json))
    }

    @TypeConverter
    fun toJson(image: AnimeImagesEntity): String {
        return Uri.decode(toJson(image))
    }
}