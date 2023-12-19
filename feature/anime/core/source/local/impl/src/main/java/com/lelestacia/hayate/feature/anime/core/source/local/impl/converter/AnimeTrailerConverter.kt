package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import android.net.Uri
import androidx.room.TypeConverter
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.trailer.AnimeTrailerEntity

internal class AnimeTrailerConverter {

    @TypeConverter
    fun fromJson(json: String): AnimeTrailerEntity {
        return fromJson(Uri.decode(json))
    }

    @TypeConverter
    fun toJson(trailer: AnimeTrailerEntity): String {
        return Uri.encode(toJson(trailer))
    }
}