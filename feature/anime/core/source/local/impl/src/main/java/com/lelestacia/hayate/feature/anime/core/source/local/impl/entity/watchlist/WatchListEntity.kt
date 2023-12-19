package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.watchlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "watchlist",
    primaryKeys = ["id", "anime_id"],
    indices = [
        Index(value = ["id", "anime_id"], unique = true)
    ]
)
data class WatchListEntity(

    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "anime_id")
    val animeId: Int,
)
