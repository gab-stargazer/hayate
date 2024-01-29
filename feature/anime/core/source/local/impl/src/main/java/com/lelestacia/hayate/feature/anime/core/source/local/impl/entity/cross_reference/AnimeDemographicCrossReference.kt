package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "anime_demographic_cross_reference",
    primaryKeys = ["anime_id", "demographic_id"]
)
data class AnimeDemographicCrossReference(

    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "demographic_id", index = true)
    val demographicId: Int
)
