package com.lelestacia.hayate.feature.anime.core.data.mapper

import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.core.domain.model.aired.AnimeAired
import com.lelestacia.hayate.feature.anime.core.domain.model.aired.AnimeProp
import com.lelestacia.hayate.feature.anime.core.domain.model.aired.AnimePropFrom
import com.lelestacia.hayate.feature.anime.core.domain.model.aired.AnimePropTo
import com.lelestacia.hayate.feature.anime.core.domain.model.broadcast.AnimeBroadcast
import com.lelestacia.hayate.feature.anime.core.domain.model.demographic.AnimeDemographic
import com.lelestacia.hayate.feature.anime.core.domain.model.genre.AnimeGenre
import com.lelestacia.hayate.feature.anime.core.domain.model.image.AnimeImages
import com.lelestacia.hayate.feature.anime.core.domain.model.image.AnimeJpg
import com.lelestacia.hayate.feature.anime.core.domain.model.image.AnimeWebp
import com.lelestacia.hayate.feature.anime.core.domain.model.licensor.AnimeLicensor
import com.lelestacia.hayate.feature.anime.core.domain.model.producer.AnimeProducer
import com.lelestacia.hayate.feature.anime.core.domain.model.studio.AnimeStudio
import com.lelestacia.hayate.feature.anime.core.domain.model.theme.AnimeTheme
import com.lelestacia.hayate.feature.anime.core.domain.model.title.AnimeTitle
import com.lelestacia.hayate.feature.anime.core.domain.model.trailer.AnimeTrailer
import com.lelestacia.hayate.feature.anime.core.domain.model.trailer.AnimeTrailerImages
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.AnimeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.aired.AnimeAiredEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.aired.AnimePropEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.aired.AnimePropFromEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.aired.AnimePropToEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.broadcast.AnimeBroadcastEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.demographic.AnimeDemographicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeExplicitGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.images.AnimeImageEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.images.AnimeJpegEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.images.AnimeWebpEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.licensor.AnimeLicensorEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.producer.AnimeProducerEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.studio.AnimeStudioEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.theme.AnimeThemeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.title.AnimeTitleEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.trailer.AnimeTrailerEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.trailer.AnimeTrailerImagesEntity
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

fun Anime.asNewEntity(): AnimeEntity {
    return AnimeEntity(
        malId = malId,
        url = url,
        images = AnimeImageEntity(
            jpg = AnimeJpegEntity(
                imageUrl = images.jpg.imageUrl,
                smallImageUrl = images.jpg.smallImageUrl,
                largeImageUrl = images.jpg.largeImageUrl,
            ),
            webp = AnimeWebpEntity(
                imageUrl = images.webp.imageUrl,
                smallImageUrl = images.webp.smallImageUrl,
                largeImageUrl = images.webp.largeImageUrl,
            )
        ),
        trailer = AnimeTrailerEntity(
            youtubeId = trailer.youtubeId,
            url = trailer.url,
            embedUrl = trailer.embedUrl,
            images = AnimeTrailerImagesEntity(
                imageUrl = trailer.images.imageUrl,
                smallImageUrl = trailer.images.smallImageUrl,
                mediumImageUrl = trailer.images.mediumImageUrl,
                largeImageUrl = trailer.images.largeImageUrl,
                maximumImageUrl = trailer.images.maximumImageUrl,
            )
        ),
        approved = approved,
        titles = AnimeTitleEntity(
            data = titles.map { title ->
                AnimeTitleEntity.Content(
                    type = title.type,
                    title = title.title
                )
            }
        ),
        title = title,
        titleEnglish = titleEnglish,
        titleJapanese = titleJapanese,
        titleSynonyms = titleSynonyms,
        type = type,
        source = source,
        episodes = episodes,
        status = status,
        airing = airing,
        aired = AnimeAiredEntity(
            from = aired.from,
            to = aired.to,
            prop = AnimePropEntity(
                AnimePropFromEntity(
                    day = aired.prop.from.day,
                    month = aired.prop.from.month,
                    year = aired.prop.from.year,
                ),
                AnimePropToEntity(
                    day = aired.prop.to.day,
                    month = aired.prop.to.month,
                    year = aired.prop.to.year,
                )
            ),
            string = aired.string
        ),
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
        broadcast = AnimeBroadcastEntity(
            day = broadcast.day,
            time = broadcast.time,
            timezone = broadcast.timezone,
            string = broadcast.string
        ),
        producers = producers.map { producer ->
            AnimeProducerEntity(
                malId = producer.malId,
                type = producer.type,
                name = producer.name,
                url = producer.url,
                createdAt = Date(),
                updatedAt = null
            )
        },
        licensors = licensors.map { licensor ->
            AnimeLicensorEntity(
                malId = licensor.malId,
                type = licensor.type,
                name = licensor.name,
                url = licensor.url,
                createdAt = Date(),
                updatedAt = null
            )
        },
        studios = studios.map { studio ->
            AnimeStudioEntity(
                malId = studio.malId,
                type = studio.type,
                name = studio.name,
                url = studio.url,
                createdAt = Date(),
                updatedAt = null
            )
        },
        genres = genres.map { genre ->
            AnimeGenreEntity(
                malId = genre.malId,
                type = genre.type,
                name = genre.name,
                url = genre.url,
                createdAt = Date(),
                updatedAt = null
            )
        },
        explicitGenres = explicitGenres.map { genre ->
            AnimeExplicitGenreEntity(
                malId = genre.malId,
                type = genre.type,
                name = genre.name,
                url = genre.url,
                createdAt = Date(),
                updatedAt = null
            )
        },
        themes = themes.map { theme ->
            AnimeThemeEntity(
                malId = theme.malId,
                type = theme.type,
                name = theme.name,
                url = theme.url,
                createdAt = Date(),
                updatedAt = null
            )
        },
        demographics = demographics.map { demographic ->
            AnimeDemographicEntity(
                malId = demographic.malId,
                type = demographic.type,
                name = demographic.name,
                url = demographic.url,
                createdAt = Date(),
                updatedAt = null
            )
        }
    )
}

fun AnimeImageEntity.asAnimeImage(): AnimeImages {
    return AnimeImages(
        jpg = AnimeJpg(
            imageUrl = jpg.imageUrl,
            smallImageUrl = jpg.smallImageUrl,
            largeImageUrl = jpg.largeImageUrl
        ),
        webp = AnimeWebp(
            imageUrl = webp.imageUrl,
            smallImageUrl = webp.smallImageUrl,
            largeImageUrl = webp.largeImageUrl
        )
    )
}

fun AnimeTrailerEntity.asAnimeTrailer(): AnimeTrailer {
    return AnimeTrailer(
        youtubeId = youtubeId,
        url = url,
        embedUrl = embedUrl,
        images = AnimeTrailerImages(
            imageUrl = images.imageUrl,
            smallImageUrl = images.smallImageUrl,
            mediumImageUrl = images.mediumImageUrl,
            largeImageUrl = images.largeImageUrl,
            maximumImageUrl = images.maximumImageUrl
        )
    )
}

fun AnimeAiredEntity.asAnimeAired(): AnimeAired {
    return AnimeAired(
        from = from,
        to = to,
        prop = AnimeProp(
            from = AnimePropFrom(
                day = prop.from.day,
                month = prop.from.month,
                year = prop.from.year
            ),
            to = AnimePropTo(
                day = prop.to.day,
                month = prop.to.month,
                year = prop.to.year,
            )
        ),
        string = string
    )
}

fun AnimeBroadcastEntity.asAnimeBroadcast(): AnimeBroadcast {
    return AnimeBroadcast(
        day = day,
        time = time,
        timezone = timezone,
        string = string
    )
}


fun AnimeEntity.asAnime(): Anime {
    return Anime(
        malId = malId,
        url = url,
        images = images.asAnimeImage(),
        trailer = trailer.asAnimeTrailer(),
        approved = approved,
        titles = titles.data.map { title ->
            AnimeTitle(
                type = title.type,
                title = title.title
            )
        },
        title = title,
        titleEnglish = titleEnglish,
        titleJapanese = titleJapanese,
        titleSynonyms = titleSynonyms,
        type = type,
        source = source,
        episodes = episodes,
        status = status,
        airing = airing,
        aired = aired.asAnimeAired(),
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
        broadcast = broadcast.asAnimeBroadcast(),
        producers = producers.map { producer ->
            AnimeProducer(
                malId = producer.malId,
                type = producer.type,
                name = producer.name,
                url = producer.url
            )
        },
        licensors = licensors.map { licensor ->
            AnimeLicensor(
                malId = licensor.malId,
                type = licensor.type,
                name = licensor.name,
                url = licensor.url,
            )
        },
        studios = studios.map { studio ->
            AnimeStudio(
                malId = studio.malId,
                type = studio.type,
                name = studio.name,
                url = studio.url,
            )
        },
        genres = genres.map { genre ->
            AnimeGenre(
                malId = genre.malId,
                type = genre.type,
                name = genre.name,
                url = genre.url
            )
        },
        explicitGenres = explicitGenres.map { genre ->
            AnimeGenre(
                malId = genre.malId,
                type = genre.type,
                name = genre.name,
                url = genre.url
            )
        },
        themes = themes.map { theme ->
            AnimeTheme(
                malId = theme.malId,
                type = theme.type,
                name = theme.name,
                url = theme.url
            )
        },
        demographics = demographics.map { demographic ->
            AnimeDemographic(
                malId = demographic.malId,
                type = demographic.type,
                name = demographic.name,
                url = demographic.url,
            )
        }
    )
}