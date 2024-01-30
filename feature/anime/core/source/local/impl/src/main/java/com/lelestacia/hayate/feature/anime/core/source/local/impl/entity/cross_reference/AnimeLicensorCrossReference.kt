package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "anime_licensor_cross_reference",
    primaryKeys = ["anime_id", "licensor_id"]
)
data class AnimeLicensorCrossReference(
    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "licensor_id", index = true)
    val licensorId: Int
)
