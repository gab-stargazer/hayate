package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "anime_producer_cross_reference",
    primaryKeys = ["anime_id", "producer_id"]
)
data class AnimeProducerCrossReference(
    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "producer_id", index = true)
    val producerId: Int
)
