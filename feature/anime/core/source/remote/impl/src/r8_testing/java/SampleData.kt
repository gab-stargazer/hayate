package com.lelestacia.hayate.feature.anime.core.source.remote.impl_test

import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.aired.AnimeAiredDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.aired.AnimePropDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.aired.AnimePropFromDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.aired.AnimePropToDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.broadcast.AnimeBroadcastDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.genre.AnimeGenreDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.image.AnimeImagesDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.image.AnimeJpgDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.image.AnimeWebpDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.producer.AnimeProducerDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.studio.AnimeStudioDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.theme.AnimeThemeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.title.AnimeTitleDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.trailer.AnimeTrailerDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.trailer.AnimeTrailerImagesDto

internal val sampleData = listOf(
    AnimeDto(
        malId = 40357,
        url = "https://myanimelist.net/…ha_no_Nariagari_Season_3",
        images = AnimeImagesDto(
            jpg = AnimeJpgDto(
                imageUrl = "https://cdn.myanimelist.net/images/anime/1317/139802.jpg",
                smallImageUrl = "https://cdn.myanimelist.net/images/anime/1317/139802t.jpg",
                largeImageUrl = "https://cdn.myanimelist.net/images/anime/1317/139802L.jpg"
            ),
            webp = AnimeWebpDto(
                imageUrl = "https://cdn.myanimelist.net/images/anime/1317/139802t.webp",
                smallImageUrl = "https://cdn.myanimelist.net/images/anime/1317/139802t.webp",
                largeImageUrl = "https://cdn.myanimelist.net/images/anime/1317/139802l.webp"
            )
        ),
        trailer = AnimeTrailerDto(
            youtubeId = "VW_LxM4tt-o",
            url = "https://www.youtube.com/watch?v=VW_LxM4tt-o",
            embedUrl = "https://www.youtube.com/embed/VW_LxM4tt-o?enablejsapi=1&wmode=opaque&autoplay=1",
            images = AnimeTrailerImagesDto(
                imageUrl = "https://img.youtube.com/vi/VW_LxM4tt-o/default.jpg",
                smallImageUrl = "https://img.youtube.com/vi/VW_LxM4tt-o/sddefault.jpg",
                mediumImageUrl = "https://img.youtube.com/vi/VW_LxM4tt-o/mqdefault.jpg",
                largeImageUrl = "https://img.youtube.com/vi/VW_LxM4tt-o/hqdefault.jpg",
                maximumImageUrl = "https://img.youtube.com/vi/VW_LxM4tt-o/maxresdefault.jpg"
            )
        ),
        approved = true,
        titles = listOf(
            AnimeTitleDto(
                type = "Default",
                title = "Tate no Yuusha no Nariagari Season 3"
            )
        ),
        title = "Tate no Yuusha no Nariagari Season 3",
        titleEnglish = "The Rising of the Shield Hero Season 3",
        titleJapanese = "盾の勇者の成り上がり Season 3",
        titleSynonyms = emptyList(),
        type = "TV",
        source = "Light novel",
        episodes = 12,
        status = "Currently Airing",
        airing = true,
        aired = AnimeAiredDto(
            from = "2023-10-06T00:00:00+00:00",
            to = null,
            prop = AnimePropDto(
                from = AnimePropFromDto(
                    day = 6,
                    month = 10,
                    year = 2023
                ),
                to = AnimePropToDto(
                    day = null,
                    month = null,
                    year = null
                )
            ),
            string = "Oct 6, 2023 to ?"
        ),
        duration = "23 min per ep",
        rating = "PG-13 - Teens 13 or older",
        score = 7.43,
        scoredBy = 22231,
        rank = 2028,
        popularity = 604,
        members = 372955,
        favorites = 5781,
        synopsis = "After defeating the Spirit Tortoise, Naofumi has no time for rest. An attack from the next Guardian Beast is imminent, but the three other Cardinal Heroes have gone missing. So, Naofumi and his party set out to search for the legendary trio.\n\n(Source: Crunchyroll)",
        background = null,
        season = "fall",
        year = 2023,
        broadcast = AnimeBroadcastDto(
            day = "Fridays",
            time = "21:00",
            timezone = "Asia/Tokyo",
            string = "Fridays at 21:00 (JST)"
        ),
        producers = listOf(
            AnimeProducerDto(
                malId = 61,
                type = "anime",
                name = "Frontier Works",
                url = "https://myanimelist.net/…oducer/61/Frontier_Works"
            )
        ),
        licensors = emptyList(),
        studios = listOf(
            AnimeStudioDto(
                malId = 290,
                type = "anime",
                name = "Kinema Citrus",
                url = "https://myanimelist.net/…oducer/290/Kinema_Citrus"
            )
        ),
        genres = listOf(
            AnimeGenreDto(
                malId = 1,
                type = "anime",
                name = "Action",
                url = "https://myanimelist.net/anime/genre/1/Action"
            )
        ),
        explicitGenres = emptyList(),
        themes = listOf(
            AnimeThemeDto(
                malId = 62,
                type = "anime",
                name = "Isekai",
                url = "https://myanimelist.net/anime/genre/62/Isekai"
            )
        ),
        demographics = emptyList()
    )
)