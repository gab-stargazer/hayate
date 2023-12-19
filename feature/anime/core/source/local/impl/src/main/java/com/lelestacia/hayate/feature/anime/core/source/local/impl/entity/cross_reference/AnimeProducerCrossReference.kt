package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.producer.AnimeProducerEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity

@Entity(
    tableName = "anime_producer_cross_reference",
    primaryKeys = ["anime_id", "producer_id"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeBasicEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["anime_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeProducerEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["producer_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class AnimeProducerCrossReference(
    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "producer_id", index = true)
    val producerId: Int
)
