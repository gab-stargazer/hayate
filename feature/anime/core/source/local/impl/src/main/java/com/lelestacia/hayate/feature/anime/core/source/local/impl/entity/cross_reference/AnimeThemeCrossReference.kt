package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "anime_theme_cross_reference",
    primaryKeys = ["anime_id", "theme_id"]
)
data class AnimeThemeCrossReference(

    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "theme_id", index = true)
    val themeId: Int
)
