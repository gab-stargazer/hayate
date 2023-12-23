package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import androidx.room.TypeConverter
import com.lelestacia.hayate.common.shared.util.fromJson as MoshiToJson
import com.lelestacia.hayate.common.shared.util.toJson as MoshiToJson

internal class ListOfStringConverter {

    @TypeConverter
    fun toJson(list: List<String>): String {
        return MoshiToJson(list)
    }

    @TypeConverter
    fun fromJson(json: String): List<String> {
        return MoshiToJson<List<String>>(json) ?: emptyList()
    }
}