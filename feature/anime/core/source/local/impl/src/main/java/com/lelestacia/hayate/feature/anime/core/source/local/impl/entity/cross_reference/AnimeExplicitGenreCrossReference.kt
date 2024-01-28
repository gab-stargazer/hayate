package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeExplicitGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity

@Entity(
    tableName = "anime_explicit_genre_cross_reference",
    primaryKeys = ["anime_id", "explicit_genre_id"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeBasicEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["anime_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeExplicitGenreEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["explicit_genre_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class AnimeExplicitGenreCrossReference(

    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "explicit_genre_id", index = true)
    val genreId: Int
)
