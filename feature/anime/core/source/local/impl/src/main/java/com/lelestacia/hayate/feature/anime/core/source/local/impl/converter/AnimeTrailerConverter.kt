package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import androidx.room.TypeConverter
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.trailer.AnimeTrailerEntity
import com.lelestacia.hayate.core.common.util.fromJson as MoshiFromJson
import com.lelestacia.hayate.core.common.util.toJson as MoshiToJson

internal class AnimeTrailerConverter {

    @TypeConverter
    fun fromJson(json: String): AnimeTrailerEntity {
        return MoshiFromJson<AnimeTrailerEntity>(json) as AnimeTrailerEntity
    }

    @TypeConverter
    fun toJson(trailer: AnimeTrailerEntity): String {
        return MoshiToJson(trailer)
    }
}