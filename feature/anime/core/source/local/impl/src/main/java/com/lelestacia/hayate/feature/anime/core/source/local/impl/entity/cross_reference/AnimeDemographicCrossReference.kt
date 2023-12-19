package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.demographic.AnimeDemographicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity

@Entity(
    tableName = "anime_demographic_cross_reference",
    primaryKeys = ["anime_id", "demographic_id"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeBasicEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["anime_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeDemographicEntity::class,
            parentColumns = ["mal_id"],
            childColumns = ["demographic_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class AnimeDemographicCrossReference(

    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "demographic_id", index = true)
    val demographicId: Int
)
