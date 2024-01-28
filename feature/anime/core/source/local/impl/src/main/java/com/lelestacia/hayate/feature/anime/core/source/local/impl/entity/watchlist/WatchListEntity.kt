package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.watchlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "watchlist",
    indices = [
        Index(value = ["anime_id"], unique = true)
    ]
)
data class WatchListEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "anime_id")
    val animeId: Int,
)
