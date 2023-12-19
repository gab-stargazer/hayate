package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.licensor.AnimeLicensorEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity

@Entity(
    tableName = "anime_licensor_cross_reference",
    primaryKeys = ["anime_id", "licensor_id"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeBasicEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["anime_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeLicensorEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["licensor_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class AnimeLicensorCrossReference(
    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "licensor_id", index = true)
    val licensorId: Int
)
