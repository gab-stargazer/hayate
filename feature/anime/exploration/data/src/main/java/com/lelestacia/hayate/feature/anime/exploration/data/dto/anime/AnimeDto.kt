package com.lelestacia.hayate.feature.anime.exploration.data.dto.anime


import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.aired.AnimeAiredDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.broadcast.AnimeBroadcastDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.demographic.AnimeDemographicDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.genre.AnimeGenreDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.image.AnimeImagesDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.licensor.AnimeLicensorDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.producer.AnimeProducerDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.studio.AnimeStudioDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.theme.AnimeThemeDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.title.AnimeTitleDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.trailer.AnimeTrailerDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeDto(

    @Json(name = "mal_id")
    val malId: Int,

    @Json(name = "url")
    val url: String,

    @Json(name = "images")
    val images: AnimeImagesDto,

    @Json(name = "trailer")
    val trailer: AnimeTrailerDto,

    @Json(name = "approved")
    val approved: Boolean,

    @Json(name = "titles")
    val titles: List<AnimeTitleDto>,

    @Json(name = "title")
    val title: String,

    @Json(name = "title_english")
    val titleEnglish: String?,

    @Json(name = "title_japanese")
    val titleJapanese: String?,

    @Json(name = "title_synonyms")
    val titleSynonyms: List<String>,

    @Json(name = "type")
    val type: String?,

    @Json(name = "source")
    val source: String,

    @Json(name = "episodes")
    val episodes: Int?,

    @Json(name = "status")
    val status: String,

    @Json(name = "airing")
    val airing: Boolean,

    @Json(name = "aired")
    val aired: AnimeAiredDto,

    @Json(name = "duration")
    val duration: String,

    @Json(name = "rating")
    val rating: String?,

    @Json(name = "score")
    val score: Double?,

    @Json(name = "scored_by")
    val scoredBy: Int?,

    @Json(name = "rank")
    val rank: Int?,

    @Json(name = "popularity")
    val popularity: Int,

    @Json(name = "members")
    val members: Int,

    @Json(name = "favorites")
    val favorites: Int,

    @Json(name = "synopsis")
    val synopsis: String?,

    @Json(name = "background")
    val background: String?,

    @Json(name = "season")
    val season: String?,

    @Json(name = "year")
    val year: Int?,

    @Json(name = "broadcast")
    val broadcast: AnimeBroadcastDto,

    @Json(name = "producers")
    val producers: List<AnimeProducerDto>,

    @Json(name = "licensors")
    val licensors: List<AnimeLicensorDto>,

    @Json(name = "studios")
    val studios: List<AnimeStudioDto>,

    @Json(name = "genres")
    val genres: List<AnimeGenreDto>,

    @Json(name = "explicit_genres")
    val explicitGenres: List<AnimeGenreDto>,

    @Json(name = "themes")
    val themes: List<AnimeThemeDto>,

    @Json(name = "demographics")
    val demographics: List<AnimeDemographicDto>
)