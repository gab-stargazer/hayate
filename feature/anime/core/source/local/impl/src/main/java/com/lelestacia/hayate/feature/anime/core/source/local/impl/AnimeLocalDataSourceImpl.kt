package com.lelestacia.hayate.feature.anime.core.source.local.impl

import com.lelestacia.hayate.common.shared.util.IoDispatcher
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
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.anime.AnimeTitleSynonymEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeDemographicCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeExplicitGenreCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeGenreCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeLicensorCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeProducerCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeStudioCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeThemeCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.mapper.asNewBasicEntity
import kotlinx.coroutines.withContext
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
    @IoDispatcher private val ioDispatcher: CoroutineContext
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

            titleDao.insertTitles(titleSynonyms)
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
}