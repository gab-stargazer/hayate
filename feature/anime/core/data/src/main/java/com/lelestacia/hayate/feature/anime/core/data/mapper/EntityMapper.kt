package com.lelestacia.hayate.feature.anime.core.data.mapper

import com.lelestacia.hayate.feature.anime.core.domain.model.demographic.AnimeDemographic
import com.lelestacia.hayate.feature.anime.core.domain.model.genre.AnimeGenre
import com.lelestacia.hayate.feature.anime.core.domain.model.theme.AnimeTheme
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.demographic.AnimeDemographicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeExplicitGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.theme.AnimeThemeEntity
import java.util.Date

fun AnimeGenre.asEntity(): AnimeGenreEntity {
    return AnimeGenreEntity(
        malId = malId,
        type = type,
        name = name,
        url = url,
        createdAt = Date(),
        updatedAt = null
    )
}

fun AnimeGenre.asExplicitEntity(): AnimeExplicitGenreEntity {
    return AnimeExplicitGenreEntity(
        malId = malId,
        type = type,
        name = name,
        url = url,
        createdAt = Date(),
        updatedAt = null
    )
}

fun AnimeTheme.asEntity(): AnimeThemeEntity {
    return AnimeThemeEntity(
        malId = malId,
        type = type,
        name = name,
        url = url,
        createdAt = Date(),
        updatedAt = null
    )
}

fun AnimeDemographic.asEntity(): AnimeDemographicEntity {
    return AnimeDemographicEntity(
        malId = malId,
        type = type,
        name = name,
        url = url,
        createdAt = Date(),
        updatedAt = null
    )
}