package com.lelestacia.hayate.feature.anime.core.source.local.impl

import com.lelestacia.hayate.common.shared.util.IoDispatcher
import com.lelestacia.hayate.feature.anime.core.source.local.api.api.AnimeLocalDataSourceApi
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.AnimeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.demographic.AnimeDemographicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeExplicitGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.theme.AnimeThemeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.AnimeDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.DemographicDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.ExplicitGenreDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.GenreDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.ThemeDao
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class AnimeLocalDataSourceImpl @Inject constructor(
    private val animeDao: AnimeDao,
    private val genreDao: GenreDao,
    private val explicitGenreDao: ExplicitGenreDao,
    private val themeDao: ThemeDao,
    private val demographicDao: DemographicDao,
    @IoDispatcher private val ioDispatcher: CoroutineContext
) : AnimeLocalDataSourceApi {

    override suspend fun insertAnime(animeEntity: AnimeEntity) {
        withContext(ioDispatcher) {
            // TODO: Implement this later
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