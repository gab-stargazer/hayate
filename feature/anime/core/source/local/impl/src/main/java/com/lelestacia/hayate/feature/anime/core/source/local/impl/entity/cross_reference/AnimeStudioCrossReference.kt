package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "anime_studio_cross_reference",
    primaryKeys = ["anime_id", "studio_id"]
)
data class AnimeStudioCrossReference(

    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "studio_id", index = true)
    val studioId: Int
)
