package com.lelestacia.hayate.feature.anime.core.source.local.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.lelestacia.hayate.core.common.util.IoDispatcher
import com.lelestacia.hayate.feature.anime.core.source.local.api.api.AnimeLocalDataSourceApi
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.AnimeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.demographic.AnimeDemographicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeExplicitGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.licensor.AnimeLicensorEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.producer.AnimeProducerEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.studio.AnimeStudioEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.theme.AnimeThemeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.AnimeDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.DemographicDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.ExplicitGenreDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.GenreDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.LicensorDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.ProducerDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.StudioDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.ThemeDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.TitleDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.WatchlistDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeFullEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.anime.AnimeTitleSynonymEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeDemographicCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeExplicitGenreCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeGenreCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeLicensorCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeProducerCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeStudioCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeThemeCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.watchlist.WatchListEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.mapper.asNewBasicEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class AnimeLocalDataSourceImpl @Inject constructor(
    private val animeDao: AnimeDao,
    private val genreDao: GenreDao,
    private val explicitGenreDao: ExplicitGenreDao,
    private val licensorDao: LicensorDao,
    private val producerDao: ProducerDao,
    private val studioDao: StudioDao,
    private val themeDao: ThemeDao,
    private val titleDao: TitleDao,
    private val demographicDao: DemographicDao,
    private val watchlistDao: WatchlistDao,
    @IoDispatcher private val ioDispatcher: CoroutineContext,
) : AnimeLocalDataSourceApi {

    override suspend fun insertAnime(animeEntity: AnimeEntity) {
        withContext(ioDispatcher) {
            val basicEntity: AnimeBasicEntity = animeEntity.asNewBasicEntity()

            val demographics: List<AnimeDemographicEntity> = animeEntity.demographics
            val demographicsReference: List<AnimeDemographicCrossReference> = demographics
                .map { demographic ->
                    AnimeDemographicCrossReference(
                        animeId = animeEntity.malId,
                        demographicId = demographic.malId
                    )
                }

            val genres: List<AnimeGenreEntity> = animeEntity.genres
            val genresReference: List<AnimeGenreCrossReference> = genres
                .map { genre ->
                    AnimeGenreCrossReference(
                        animeId = animeEntity.malId,
                        genreId = genre.malId
                    )
                }

            val explicitGenres: List<AnimeExplicitGenreEntity> = animeEntity.explicitGenres
            val explicitGenresReference = explicitGenres
                .map { explicitGenre ->
                    AnimeExplicitGenreCrossReference(
                        animeId = animeEntity.malId,
                        genreId = explicitGenre.malId
                    )
                }

            val licensors: List<AnimeLicensorEntity> = animeEntity.licensors
            val licensorReference: List<AnimeLicensorCrossReference> = licensors
                .map { licensor ->
                    AnimeLicensorCrossReference(
                        animeId = animeEntity.malId,
                        licensorId = licensor.malId
                    )
                }

            val producers: List<AnimeProducerEntity> = animeEntity.producers
            val producerReference = producers
                .map { producer ->
                    AnimeProducerCrossReference(
                        animeId = animeEntity.malId,
                        producerId = producer.malId
                    )
                }

            val studios: List<AnimeStudioEntity> = animeEntity.studios
            val studioReference = studios
                .map { studio ->
                    AnimeStudioCrossReference(
                        animeId = animeEntity.malId,
                        studioId = studio.malId
                    )
                }

            val themes: List<AnimeThemeEntity> = animeEntity.themes
            val themeReference = themes
                .map { theme ->
                    AnimeThemeCrossReference(
                        animeId = animeEntity.malId,
                        themeId = theme.malId
                    )
                }

            val titleSynonyms: List<AnimeTitleSynonymEntity> = animeEntity.titleSynonyms
                .map { title ->
                    AnimeTitleSynonymEntity(
                        title = title,
                        animeId = animeEntity.malId
                    )
                }

            //  Insert everything into DB
            //  For entity that has reference, insert entity first then reference
            animeDao.insertAnime(basicEntity)

            with(demographicDao) {
                insertDemographics(demographics)
                insertCrossReferences(demographicsReference)
            }

            with(genreDao) {
                insertGenres(genres)
                insertCrossReferences(genresReference)
            }

            with(explicitGenreDao) {
                insertExplicitGenres(explicitGenres)
                insertCrossReferences(explicitGenresReference)
            }

            with(licensorDao) {
                insertLicensors(licensors)
                insertCrossReferences(licensorReference)
            }

            with(producerDao) {
                insertProducers(producers)
                insertCrossReferences(producerReference)
            }

            with(studioDao) {
                insertStudios(studios)
                insertCrossReferences(studioReference)
            }

            with(themeDao) {
                insertThemes(themes)
                insertCrossReferences(themeReference)
            }

            titleDao.insertTitleSynonyms(titleSynonyms)
        }
    }

    override suspend fun insertGenres(genres: List<AnimeGenreEntity>) {
        withContext(ioDispatcher) {
            genreDao.insertGenres(genres)
        }
    }

    override suspend fun insertExplicitGenres(explicitGenres: List<AnimeExplicitGenreEntity>) {
        withContext(ioDispatcher) {
            explicitGenreDao.insertExplicitGenres(explicitGenres)
        }
    }

    override suspend fun insertThemes(themes: List<AnimeThemeEntity>) {
        withContext(ioDispatcher) {
            themeDao.insertThemes(themes)
        }
    }

    override suspend fun insertDemographics(demographics: List<AnimeDemographicEntity>) {
        withContext(ioDispatcher) {
            demographicDao.insertDemographics(demographics)
        }
    }

    override suspend fun getAnimeByAnimeID(animeID: Int): AnimeEntity {
        return withContext(ioDispatcher) {

            val entity: AnimeFullEntity = animeDao.getAnimeByAnimeId(animeID)
            val animeEntity = AnimeEntity(
                malId = entity.anime.malId,
                url = entity.anime.url,
                images = entity.anime.images,
                trailer = entity.anime.trailer,
                approved = entity.anime.approved,
                titles = entity.anime.titles,
                title = entity.anime.title,
                titleEnglish = entity.anime.titleEnglish,
                titleJapanese = entity.anime.titleJapanese,
                titleSynonyms = entity.titleSynonym.map { titleSynonym ->
                    titleSynonym.title
                },
                type = entity.anime.type,
                source = entity.anime.source,
                episodes = entity.anime.episodes,
                status = entity.anime.status,
                airing = entity.anime.airing,
                aired = entity.anime.aired,
                duration = entity.anime.duration,
                rating = entity.anime.rating,
                score = entity.anime.score,
                scoredBy = entity.anime.scoredBy,
                rank = entity.anime.rank,
                popularity = entity.anime.popularity,
                members = entity.anime.members,
                favorites = entity.anime.favorites,
                synopsis = entity.anime.synopsis,
                background = entity.anime.background,
                season = entity.anime.season,
                year = entity.anime.year,
                broadcast = entity.anime.broadcast,
                producers = entity.producers,
                licensors = entity.licensors,
                studios = entity.studios,
                genres = entity.genres,
                explicitGenres = entity.explicitGenres,
                themes = entity.themes,
                demographics = entity.demographics
            )

            Timber.d("Queried Anime: %s", entity.anime.toString())
            Timber.d("Queried Producer: %s", animeEntity.producers.toString())
            Timber.d("Queried Licensor: %s", animeEntity.licensors.toString())
            Timber.d("Queried Studio: %s", animeEntity.studios.toString())
            Timber.d("Queried Genre: %s", entity.genres.toString())
            Timber.d("Queried Explicit Genre: %s", animeEntity.explicitGenres.toString())
            Timber.d("Queried Theme: %s", entity.themes.toString())
            Timber.d("Queried Demographic: %s", animeEntity.demographics.toString())
            return@withContext animeEntity
        }
    }

    override fun getAnimeHistory(): Flow<PagingData<AnimeEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 24
            ),
            pagingSourceFactory = {
                animeDao.getAnimeHistory()
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                AnimeEntity(
                    malId = entity.anime.malId,
                    url = entity.anime.url,
                    images = entity.anime.images,
                    trailer = entity.anime.trailer,
                    approved = entity.anime.approved,
                    titles = entity.anime.titles,
                    title = entity.anime.title,
                    titleEnglish = entity.anime.titleEnglish,
                    titleJapanese = entity.anime.titleJapanese,
                    titleSynonyms = entity.titleSynonym.map { titleSynonym ->
                        titleSynonym.title
                    },
                    type = entity.anime.type,
                    source = entity.anime.source,
                    episodes = entity.anime.episodes,
                    status = entity.anime.status,
                    airing = entity.anime.airing,
                    aired = entity.anime.aired,
                    duration = entity.anime.duration,
                    rating = entity.anime.rating,
                    score = entity.anime.score,
                    scoredBy = entity.anime.scoredBy,
                    rank = entity.anime.rank,
                    popularity = entity.anime.popularity,
                    members = entity.anime.members,
                    favorites = entity.anime.favorites,
                    synopsis = entity.anime.synopsis,
                    background = entity.anime.background,
                    season = entity.anime.season,
                    year = entity.anime.year,
                    broadcast = entity.anime.broadcast,
                    producers = entity.producers,
                    licensors = entity.licensors,
                    studios = entity.studios,
                    genres = entity.genres,
                    explicitGenres = entity.explicitGenres,
                    themes = entity.themes,
                    demographics = entity.demographics
                )
            }
        }
    }

    override suspend fun insertWatchList(animeID: Int) {
        try {
            watchlistDao.insertWatchList(
                WatchListEntity(
                    animeId = animeID
                )
            )
        } catch (e: Exception) {
            Timber.w("Watchlist exist, current watchlist will be deleted")
            watchlistDao.deleteWatchList(WatchListEntity(animeID))
        }
    }

    override fun getAnimeWatchList(): Flow<PagingData<AnimeEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 24
            ),
            pagingSourceFactory = {
                animeDao.getAnimeWatchList()
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                AnimeEntity(
                    malId = entity.anime.malId,
                    url = entity.anime.url,
                    images = entity.anime.images,
                    trailer = entity.anime.trailer,
                    approved = entity.anime.approved,
                    titles = entity.anime.titles,
                    title = entity.anime.title,
                    titleEnglish = entity.anime.titleEnglish,
                    titleJapanese = entity.anime.titleJapanese,
                    titleSynonyms = entity.titleSynonym.map { titleSynonym ->
                        titleSynonym.title
                    },
                    type = entity.anime.type,
                    source = entity.anime.source,
                    episodes = entity.anime.episodes,
                    status = entity.anime.status,
                    airing = entity.anime.airing,
                    aired = entity.anime.aired,
                    duration = entity.anime.duration,
                    rating = entity.anime.rating,
                    score = entity.anime.score,
                    scoredBy = entity.anime.scoredBy,
                    rank = entity.anime.rank,
                    popularity = entity.anime.popularity,
                    members = entity.anime.members,
                    favorites = entity.anime.favorites,
                    synopsis = entity.anime.synopsis,
                    background = entity.anime.background,
                    season = entity.anime.season,
                    year = entity.anime.year,
                    broadcast = entity.anime.broadcast,
                    producers = entity.producers,
                    licensors = entity.licensors,
                    studios = entity.studios,
                    genres = entity.genres,
                    explicitGenres = entity.explicitGenres,
                    themes = entity.themes,
                    demographics = entity.demographics
                )
            }
        }
    }

    override fun getWatchListByAnimeID(animeID: Int): Flow<Boolean> {
        return watchlistDao.getWatchlistByID(animeID).map { entity ->
            entity != null
        }
    }
}