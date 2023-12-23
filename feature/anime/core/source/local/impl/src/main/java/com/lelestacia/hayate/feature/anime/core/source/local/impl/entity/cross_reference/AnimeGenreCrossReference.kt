package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity

@Entity(
    tableName = "anime_genre_cross_reference",
    primaryKeys = ["anime_id", "genre_id"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeBasicEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["anime_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeGenreEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["genre_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class AnimeGenreCrossReference(

    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "genre_id", index = true)
    val genreId: Int
)
