package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import android.net.Uri
import androidx.room.TypeConverter
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.title.AnimeTitleEntity

internal class AnimeTitlesConverter {

    @TypeConverter
    fun fromJson(json: String): List<AnimeTitleEntity> {
        return fromJson(Uri.decode(json))
    }

    @TypeConverter
    fun toJson(titles: List<AnimeTitleEntity>): String {
        return Uri.encode(toJson(titles))
    }
}