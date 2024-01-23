package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import androidx.room.TypeConverter
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.images.AnimeImageEntity
import com.lelestacia.hayate.core.common.util.fromJson as MoshiFromJson
import com.lelestacia.hayate.core.common.util.toJson as MoshiToJson

internal class AnimeImageConverter {

    @TypeConverter
    fun fromJson(json: String): AnimeImageEntity {
        return MoshiFromJson<AnimeImageEntity>(json) as AnimeImageEntity
    }

    @TypeConverter
    fun toJson(image: AnimeImageEntity): String {
        return MoshiToJson(image)
    }
}