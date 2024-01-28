package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import androidx.room.TypeConverter
import java.util.Date

internal class DateConverter {

    @TypeConverter
    fun fromLong(date: Long): Date {
        return Date(date)
    }

    @TypeConverter
    fun toLong(date: Date): Long {
        return date.time
    }
}