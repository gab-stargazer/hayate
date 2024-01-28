package com.lelestacia.hayate.feature.anime.core.source.local.api.entity

import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.aired.AnimeAiredEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.broadcast.AnimeBroadcastEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.demographic.AnimeDemographicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeExplicitGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.images.AnimeImageEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.licensor.AnimeLicensorEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.producer.AnimeProducerEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.studio.AnimeStudioEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.theme.AnimeThemeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.title.AnimeTitleEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.trailer.AnimeTrailerEntity

data class AnimeEntity(
    val malId: Int,
    val url: String,
    val images: AnimeImageEntity,
    val trailer: AnimeTrailerEntity,
    val approved: Boolean,
    val titles: AnimeTitleEntity,
    val title: String,
    val titleEnglish: String?,
    val titleJapanese: String?,
    val titleSynonyms: List<String>,
    val type: String?,
    val source: String,
    val episodes: Int?,
    val status: String,
    val airing: Boolean,
    val aired: AnimeAiredEntity,
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
    val broadcast: AnimeBroadcastEntity,
    val producers: List<AnimeProducerEntity>,
    val licensors: List<AnimeLicensorEntity>,
    val studios: List<AnimeStudioEntity>,
    val genres: List<AnimeGenreEntity>,
    val explicitGenres: List<AnimeExplicitGenreEntity>,
    val themes: List<AnimeThemeEntity>,
    val demographics: List<AnimeDemographicEntity>,
)
