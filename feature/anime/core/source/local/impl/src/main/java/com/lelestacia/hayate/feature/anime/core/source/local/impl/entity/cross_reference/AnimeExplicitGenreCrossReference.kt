package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "anime_explicit_genre_cross_reference",
    primaryKeys = ["anime_id", "explicit_genre_id"]
)
data class AnimeExplicitGenreCrossReference(

    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "explicit_genre_id", index = true)
    val genreId: Int
)
