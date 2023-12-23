package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.aired.AnimeAiredEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.broadcast.AnimeBroadcastEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.images.AnimeImageEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.title.AnimeTitleEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.trailer.AnimeTrailerEntity
import java.util.Date

@Entity(
    tableName = "anime",
    indices = [Index(value = ["mal_id"], unique = true)]
)
data class AnimeBasicEntity(

    @PrimaryKey
    @ColumnInfo(name = "mal_id")
    val malId: Int,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "images")
    val images: AnimeImageEntity,

    @ColumnInfo(name = "trailer")
    val trailer: AnimeTrailerEntity,

    @ColumnInfo(name = "approved")
    val approved: Boolean,

    @ColumnInfo(name = "titles")
    val titles: List<AnimeTitleEntity>,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "title_english")
    val titleEnglish: String?,

    @ColumnInfo(name = "title_japanese")
    val titleJapanese: String?,

    @ColumnInfo(name = "type")
    val type: String?,

    @ColumnInfo(name = "source")
    val source: String,

    @ColumnInfo(name = "episodes")
    val episodes: Int?,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "airing")
    val airing: Boolean,

    @ColumnInfo(name = "aired")
    val aired: AnimeAiredEntity,

    @ColumnInfo(name = "duration")
    val duration: String,

    @ColumnInfo(name = "rating")
    val rating: String?,

    @ColumnInfo(name = "score")
    val score: Double?,

    @ColumnInfo(name = "scored_by")
    val scoredBy: Int?,

    @ColumnInfo(name = "rank")
    val rank: Int?,

    @ColumnInfo(name = "popularity")
    val popularity: Int,

    @ColumnInfo(name = "members")
    val members: Int,

    @ColumnInfo(name = "favorites")
    val favorites: Int,

    @ColumnInfo(name = "synopsis")
    val synopsis: String?,

    @ColumnInfo(name = "background")
    val background: String?,

    @ColumnInfo(name = "season")
    val season: String?,

    @ColumnInfo(name = "year")
    val year: Int?,

    @Embedded
    val broadcast: AnimeBroadcastEntity,

//    Commented due to changed into separate table
//
//    @Json(name = "producers")
//    val producers: List<AnimeProducerEntity>,
//
//    @Json(name = "licensors")
//    val licensors: List<AnimeLicensorEntity>,
//
//    @Json(name = "studios")
//    val studios: List<AnimeStudioEntity>,
//
//    @Json(name = "genres")
//    val genres: List<AnimeGenreEntity>,
//
//    @Json(name = "explicit_genres")
//    val explicitGenres: List<AnimeGenreEntity>,
//
//    @Json(name = "themes")
//    val themes: List<AnimeThemeEntity>,
//
//    @Json(name = "demographics")
//    val demographics: List<AnimeDemographicEntity>,

    @ColumnInfo(name = "created_at")
    val createdAt: Date,

    @ColumnInfo(name = "updated_at")
    var updatedAt: Date?
)
