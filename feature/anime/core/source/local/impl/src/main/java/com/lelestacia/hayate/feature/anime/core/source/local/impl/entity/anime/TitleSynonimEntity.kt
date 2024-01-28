package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.anime

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "title_synonym"
)
data class AnimeTitleSynonymEntity(

    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String
)
