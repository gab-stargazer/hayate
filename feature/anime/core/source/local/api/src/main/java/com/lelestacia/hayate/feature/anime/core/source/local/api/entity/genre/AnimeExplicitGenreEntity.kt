package com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "explicit_genre",
    indices = [Index(value = ["mal_id"], unique = true)]
)
data class AnimeExplicitGenreEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mal_id")
    val malId: Int,

    @ColumnInfo(name = "type")
    val type: String?,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Date,

    @ColumnInfo(name = "updated_at")
    var updatedAt: Date?
)
