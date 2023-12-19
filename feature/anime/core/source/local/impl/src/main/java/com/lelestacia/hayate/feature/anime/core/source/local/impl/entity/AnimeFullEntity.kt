package com.lelestacia.hayate.feature.anime.core.source.local.impl.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.demographic.AnimeDemographicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeExplicitGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.licensor.AnimeLicensorEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.producer.AnimeProducerEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.studio.AnimeStudioEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.theme.AnimeThemeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeDemographicCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeExplicitGenreCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeGenreCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeLicensorCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeProducerCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeStudioCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeThemeCrossReference

data class AnimeFullEntity(

    @Embedded
    val anime: AnimeBasicEntity,

    @Relation(
        parentColumn = "mal_id",
        entityColumn = "mal_id",
        associateBy = Junction(
            value = AnimeProducerCrossReference::class,
            parentColumn = "anime_id",
            entityColumn = "producer_id"
        )
    )
    val producers: List<AnimeProducerEntity>,

    @Relation(
        parentColumn = "mal_id",
        entityColumn = "mal_id",
        associateBy = Junction(
            value = AnimeLicensorCrossReference::class,
            parentColumn = "anime_id",
            entityColumn = "licensor_id"
        )
    )
    val licensors: List<AnimeLicensorEntity>,

    @Relation(
        parentColumn = "mal_id",
        entityColumn = "mal_id",
        associateBy = Junction(
            value = AnimeStudioCrossReference::class,
            parentColumn = "anime_id",
            entityColumn = "studio_id"
        )
    )
    val studios: List<AnimeStudioEntity>,

    @Relation(
        parentColumn = "mal_id",
        entityColumn = "mal_id",
        associateBy = Junction(
            value = AnimeGenreCrossReference::class,
            parentColumn = "anime_id",
            entityColumn = "genre_id"
        )
    )
    val genres: List<AnimeGenreEntity>,

    @Relation(
        parentColumn = "mal_id",
        entityColumn = "mal_id",
        associateBy = Junction(
            value = AnimeExplicitGenreCrossReference::class,
            parentColumn = "anime_id",
            entityColumn = "explicit_genre_id"
        )
    )
    val explicitGenres: List<AnimeExplicitGenreEntity>,

    @Relation(
        parentColumn = "mal_id",
        entityColumn = "mal_id",
        associateBy = Junction(
            value = AnimeThemeCrossReference::class,
            parentColumn = "anime_id",
            entityColumn = "theme_id"
        )
    )
    val themes: List<AnimeThemeEntity>,

    @Relation(
        parentColumn = "mal_id",
        entityColumn = "mal_id",
        associateBy = Junction(
            value = AnimeDemographicCrossReference::class,
            parentColumn = "anime_id",
            entityColumn = "demographic_id"
        )
    )
    val demographics: List<AnimeDemographicEntity>,
)
