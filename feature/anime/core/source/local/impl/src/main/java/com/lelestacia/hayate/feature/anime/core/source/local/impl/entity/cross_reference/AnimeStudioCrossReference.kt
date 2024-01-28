package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.studio.AnimeStudioEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity

@Entity(
    tableName = "anime_studio_cross_reference",
    primaryKeys = ["anime_id", "studio_id"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeBasicEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["anime_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeStudioEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["studio_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class AnimeStudioCrossReference(

    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "studio_id", index = true)
    val studioId: Int
)
