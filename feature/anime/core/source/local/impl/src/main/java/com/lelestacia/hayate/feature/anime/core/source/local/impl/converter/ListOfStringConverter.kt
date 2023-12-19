package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import androidx.room.TypeConverter

internal class ListOfStringConverter {

    @TypeConverter
    fun toJson(list: List<String>): String {
        return toJson(list)
    }

    @TypeConverter
    fun fromJson(json: String): List<String> {
        return fromJson(json)
    }
}