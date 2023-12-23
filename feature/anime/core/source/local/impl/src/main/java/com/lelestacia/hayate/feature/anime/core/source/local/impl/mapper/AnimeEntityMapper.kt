package com.lelestacia.hayate.feature.anime.core.source.local.impl.mapper

import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.AnimeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity
import java.util.Date

fun AnimeEntity.asNewBasicEntity(): AnimeBasicEntity {
    return AnimeBasicEntity(
        malId = malId,
        images = images,
        trailer = trailer,
        url = url,
        approved = approved,
        titles = titles,
        title = title,
        titleEnglish = titleEnglish,
        titleJapanese = titleJapanese,
        type = type,
        source = source,
        episodes = episodes,
        status = status,
        airing = airing,
        aired = aired,
        duration = duration,
        rating = rating,
        score = score,
        scoredBy = scoredBy,
        rank = rank,
        popularity = popularity,
        members = members,
        favorites = favorites,
        synopsis = synopsis,
        background = background,
        season = season,
        year = year,
        broadcast = broadcast,
        createdAt = Date(),
        updatedAt = null
    )
}