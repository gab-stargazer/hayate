package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "anime_genre_cross_reference",
    primaryKeys = ["anime_id", "genre_id"]
)
data class AnimeGenreCrossReference(

    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "genre_id", index = true)
    val genreId: Int
)
