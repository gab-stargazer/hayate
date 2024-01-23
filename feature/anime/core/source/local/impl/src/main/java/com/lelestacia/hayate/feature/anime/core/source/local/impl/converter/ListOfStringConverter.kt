package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import androidx.room.TypeConverter
import com.lelestacia.hayate.core.common.util.fromJson as MoshiFromJson
import com.lelestacia.hayate.core.common.util.toJson as MoshiToJson

internal class ListOfStringConverter {

    @TypeConverter
    fun toJson(list: List<String>): String {
        return MoshiToJson(list)
    }

    @TypeConverter
    fun fromJson(json: String): List<String> {
        return MoshiFromJson<List<String>>(json) ?: emptyList()
    }
}