package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import android.net.Uri
import androidx.room.TypeConverter
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.aired.AnimeAiredEntity

internal class AnimeAiredConverter {

    @TypeConverter
    fun fromJson(json: String): AnimeAiredEntity {
        return fromJson(Uri.decode(json))
    }

    @TypeConverter
    fun toJson(aired: AnimeAiredEntity): String {
        return Uri.encode(toJson(aired))
    }
}