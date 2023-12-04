package com.lelestacia.hayate.feature.anime.shared.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.lelestacia.hayate.feature.anime.shared.model.aired.AnimeAired
import com.lelestacia.hayate.feature.anime.shared.model.broadcast.AnimeBroadcast
import com.lelestacia.hayate.feature.anime.shared.model.demographic.AnimeDemographic
import com.lelestacia.hayate.feature.anime.shared.model.genre.AnimeGenre
import com.lelestacia.hayate.feature.anime.shared.model.image.AnimeImages
import com.lelestacia.hayate.feature.anime.shared.model.licensor.AnimeLicensor
import com.lelestacia.hayate.feature.anime.shared.model.producer.AnimeProducer
import com.lelestacia.hayate.feature.anime.shared.model.studio.AnimeStudio
import com.lelestacia.hayate.feature.anime.shared.model.theme.AnimeTheme
import com.lelestacia.hayate.feature.anime.shared.model.title.AnimeTitle
import com.lelestacia.hayate.feature.anime.shared.model.trailer.AnimeTrailer
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class Anime(
    val malId: Int,
    val url: String,
    val images: AnimeImages,
    val trailer: AnimeTrailer,
    val approved: Boolean,
    val titles: List<AnimeTitle>,
    val title: String,
    val titleEnglish: String?,
    val titleJapanese: String?,
    val titleSynonyms: List<String>,
    val type: String?,
    val source: String,
    val episodes: Int?,
    val status: String,
    val airing: Boolean,
    val aired: AnimeAired,
    val duration: String,
    val rating: String?,
    val score: Double?,
    val scoredBy: Int?,
    val rank: Int?,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val synopsis: String?,
    val background: String?,
    val season: String?,
    val year: Int?,
    val broadcast: AnimeBroadcast,
    val producers: List<AnimeProducer>,
    val licensors: List<AnimeLicensor>,
    val studios: List<AnimeStudio>,
    val genres: List<AnimeGenre>,
    val explicitGenres: List<AnimeGenre>,
    val themes: List<AnimeTheme>,
    val demographics: List<AnimeDemographic>
) : Parcelable
