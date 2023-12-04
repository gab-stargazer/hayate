package com.lelestacia.hayate.feature.anime.exploration.data.mapper

import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.demographic.AnimeDemographicDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.genre.AnimeGenreDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.licensor.AnimeLicensorDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.producer.AnimeProducerDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.studio.AnimeStudioDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.theme.AnimeThemeDto
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.title.AnimeTitleDto
import com.lelestacia.hayate.feature.anime.shared.model.Anime
import com.lelestacia.hayate.feature.anime.shared.model.aired.AnimeAired
import com.lelestacia.hayate.feature.anime.shared.model.aired.AnimeProp
import com.lelestacia.hayate.feature.anime.shared.model.aired.AnimePropFrom
import com.lelestacia.hayate.feature.anime.shared.model.aired.AnimePropTo
import com.lelestacia.hayate.feature.anime.shared.model.broadcast.AnimeBroadcast
import com.lelestacia.hayate.feature.anime.shared.model.demographic.AnimeDemographic
import com.lelestacia.hayate.feature.anime.shared.model.genre.AnimeGenre
import com.lelestacia.hayate.feature.anime.shared.model.image.AnimeImages
import com.lelestacia.hayate.feature.anime.shared.model.image.AnimeJpg
import com.lelestacia.hayate.feature.anime.shared.model.image.AnimeWebp
import com.lelestacia.hayate.feature.anime.shared.model.licensor.AnimeLicensor
import com.lelestacia.hayate.feature.anime.shared.model.producer.AnimeProducer
import com.lelestacia.hayate.feature.anime.shared.model.studio.AnimeStudio
import com.lelestacia.hayate.feature.anime.shared.model.theme.AnimeTheme
import com.lelestacia.hayate.feature.anime.shared.model.title.AnimeTitle
import com.lelestacia.hayate.feature.anime.shared.model.trailer.AnimeTrailer
import com.lelestacia.hayate.feature.anime.shared.model.trailer.AnimeTrailerImages

fun AnimeDto.asAnime(): Anime {
    return Anime(
        malId = malId,
        url = url,
        images = AnimeImages(
            jpg = AnimeJpg(
                imageUrl = images.jpg.imageUrl,
                smallImageUrl = images.jpg.smallImageUrl,
                largeImageUrl = images.jpg.largeImageUrl
            ),
            webp = AnimeWebp(
                imageUrl = images.webp.imageUrl,
                smallImageUrl = images.webp.smallImageUrl,
                largeImageUrl = images.webp.largeImageUrl
            )
        ),
        trailer = AnimeTrailer(
            youtubeId = trailer.youtubeId,
            url = trailer.url,
            images = AnimeTrailerImages(
                imageUrl = trailer.images.imageUrl,
                smallImageUrl = trailer.images.smallImageUrl,
                mediumImageUrl = trailer.images.mediumImageUrl,
                largeImageUrl = trailer.images.largeImageUrl,
                maximumImageUrl = trailer.images.maximumImageUrl
            ),
            embedUrl = trailer.embedUrl
        ),
        approved = approved,
        titles = titles.map { dto: AnimeTitleDto ->
            AnimeTitle(
                dto.type,
                dto.title
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
        aired = AnimeAired(
            from = aired.from,
            to = aired.to,
            prop = AnimeProp(
                from = AnimePropFrom(
                    day = aired.prop.from.day,
                    month = aired.prop.from.month,
                    year = aired.prop.from.year,
                ),
                to = AnimePropTo(
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
        broadcast = AnimeBroadcast(
            day = broadcast.day,
            time = broadcast.time,
            timezone = broadcast.timezone,
            string = broadcast.string
        ),
        producers = producers.map { dto: AnimeProducerDto ->
            AnimeProducer(
                malId = dto.malId,
                type = dto.type,
                name = dto.name,
                url = dto.url
            )
        },
        licensors = licensors.map { dto: AnimeLicensorDto ->
            AnimeLicensor(
                malId = dto.malId,
                type = dto.type,
                name = dto.name,
                url = dto.url
            )
        },
        studios = studios.map { dto: AnimeStudioDto ->
            AnimeStudio(
                malId = dto.malId,
                type = dto.type,
                name = dto.name,
                url = dto.url
            )
        },
        genres = genres.map { dto: AnimeGenreDto ->
            AnimeGenre(
                malId = dto.malId,
                type = dto.type,
                name = dto.name,
                url = dto.url
            )
        },
        explicitGenres = explicitGenres.map { dto: AnimeGenreDto ->
            AnimeGenre(
                malId = dto.malId,
                type = dto.type,
                name = dto.name,
                url = dto.url
            )
        },
        themes = themes.map { dto: AnimeThemeDto ->
            AnimeTheme(
                malId = dto.malId,
                type = dto.type,
                name = dto.name,
                url = dto.url
            )
        },
        demographics = demographics.map { dto: AnimeDemographicDto ->
            AnimeDemographic(
                malId = dto.malId,
                type = dto.type,
                name = dto.name,
                url = dto.url
            )
        }
    )
}