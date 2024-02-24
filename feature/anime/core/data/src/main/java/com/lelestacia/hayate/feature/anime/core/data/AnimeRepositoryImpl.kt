package com.lelestacia.hayate.feature.anime.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.lelestacia.hayate.core.common.api.LoggerApi
import com.lelestacia.hayate.core.common.state.UiState
import com.lelestacia.hayate.core.common.util.Msg
import com.lelestacia.hayate.core.common.util.Source
import com.lelestacia.hayate.core.common.util.UiText
import com.lelestacia.hayate.feature.anime.core.data.mapper.asAnime
import com.lelestacia.hayate.feature.anime.core.data.mapper.asDemographic
import com.lelestacia.hayate.feature.anime.core.data.mapper.asEntity
import com.lelestacia.hayate.feature.anime.core.data.mapper.asExplicitEntity
import com.lelestacia.hayate.feature.anime.core.data.mapper.asGenre
import com.lelestacia.hayate.feature.anime.core.data.mapper.asNewEntity
import com.lelestacia.hayate.feature.anime.core.data.mapper.asTheme
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.core.domain.model.demographic.AnimeDemographic
import com.lelestacia.hayate.feature.anime.core.domain.model.genre.AnimeGenre
import com.lelestacia.hayate.feature.anime.core.domain.model.theme.AnimeTheme
import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import com.lelestacia.hayate.feature.anime.core.source.local.api.api.AnimeLocalDataSourceApi
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.AnimeEntity
import com.lelestacia.hayate.feature.anime.core.source.remote.api.api.AnimeRemoteDataSourceApi
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.demographic.AnimeDemographicDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.genre.AnimeGenreDto
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.theme.AnimeThemeDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import kotlin.time.measureTime

internal class AnimeRepositoryImpl @Inject constructor(
    private val remoteDataSource: AnimeRemoteDataSourceApi,
    private val localDataSource: AnimeLocalDataSourceApi,
    private val loggerApi: LoggerApi,
) : AnimeRepository {

    private val source = Source(name = "REPOSITORY")

    override fun getTopAnime(
        type: String?,
        filter: String?,
        rating: String?,
    ): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 24,
                prefetchDistance = 6,
                enablePlaceholders = false,
                initialLoadSize = 24
            ),
            pagingSourceFactory = {
                remoteDataSource.getTopAnime(type, filter, rating)
            }
        ).flow.map { pagingData: PagingData<AnimeDto> ->
            pagingData.map(AnimeDto::asAnime)
        }
    }

    override fun getCurrentSeasonAnime(
        filter: String?,
        sfw: Boolean,
    ): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 24,
                prefetchDistance = 6,
                enablePlaceholders = false,
                initialLoadSize = 24
            ),
            pagingSourceFactory = {
                remoteDataSource.getCurrentSeasonAnime(filter, sfw)
            }
        ).flow.map { pagingData: PagingData<AnimeDto> ->
            pagingData.map(AnimeDto::asAnime)
        }
    }

    override fun getUpcomingSeasonAnime(
        filter: String?,
        sfw: Boolean,
    ): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 24,
                prefetchDistance = 6,
                enablePlaceholders = false,
                initialLoadSize = 24
            ),
            pagingSourceFactory = {
                remoteDataSource.getUpcomingSeasonAnime(filter, sfw)
            }
        ).flow.map { pagingData: PagingData<AnimeDto> ->
            pagingData.map(AnimeDto::asAnime)
        }
    }

    override fun getScheduledAnime(
        filter: String?,
        sfw: Boolean,
    ): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 24,
                prefetchDistance = 6,
                enablePlaceholders = false,
                initialLoadSize = 24
            ),
            pagingSourceFactory = {
                remoteDataSource.getScheduledAnime(filter, sfw)
            }
        ).flow.map { pagingData: PagingData<AnimeDto> ->
            pagingData.map(AnimeDto::asAnime)
        }
    }

    override fun getAnimeSearch(
        query: String,
        type: String?,
        rating: String?,
    ): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 24,
                prefetchDistance = 6,
                enablePlaceholders = false,
                initialLoadSize = 24
            ),
            pagingSourceFactory = {
                remoteDataSource.getAnimeSearch(
                    query = query,
                    type = type,
                    rating = rating
                )
            }
        ).flow.map { pagingData: PagingData<AnimeDto> ->
            pagingData.map(AnimeDto::asAnime)
        }
    }

    override fun initiateApp(): Flow<UiState<Boolean>> {
        return flow<UiState<Boolean>> {
            with(loggerApi) {

                // ================================Retrieval======================================//
                logDebug(
                    source = source,
                    msg = Msg("Starting to fetch Genres and Explicit Genres From API")
                )
                val genres = remoteDataSource.getGenres()
                val explicitGenres = remoteDataSource.getExplicitGenres()
                logDebug(
                    source = source,
                    msg = Msg("Finished fetching Genres and Explicit Genres From API")
                )
                // ===============================================================================//

                // ================================Insertion======================================//
                logDebug(
                    source = source,
                    msg = Msg("Starting Genres Insertion")
                )
                localDataSource.insertGenres(
                    genres
                        .asSequence()
                        .map(AnimeGenreDto::asGenre)
                        .map(AnimeGenre::asEntity)
                        .toList()
                )
                logDebug(
                    source = source,
                    msg = Msg("Finished Genres Insertion")
                )
                // ===============================================================================//

                // ================================Insertion======================================//
                logDebug(
                    source = source,
                    msg = Msg("Starting Explicit Genres Insertion")
                )
                localDataSource.insertExplicitGenres(
                    explicitGenres
                        .asSequence()
                        .map(AnimeGenreDto::asGenre)
                        .map(AnimeGenre::asExplicitEntity)
                        .toList()
                )
                logDebug(
                    source = source,
                    msg = Msg("Finished Explicit Genres Insertion")
                )
                // ===============================================================================//

                //  Short delay to avoid API Limit
                delay(1500)

                // ================================Retrieval======================================//
                logDebug(
                    source = source,
                    msg = Msg("Starting to fetch Themes and Demographics From API")
                )
                val themes = remoteDataSource.getThemes()
                val demographics = remoteDataSource.getDemographics()
                logDebug(
                    source = source,
                    msg = Msg("Finished fetching Themes and Demographics From API")
                )
                // ===============================================================================//

                // ================================Insertion======================================//
                logDebug(
                    source = source,
                    msg = Msg("Starting Themes Insertion")
                )
                localDataSource.insertThemes(
                    themes
                        .asSequence()
                        .map(AnimeThemeDto::asTheme)
                        .map(AnimeTheme::asEntity)
                        .toList()
                )
                logDebug(
                    source = source,
                    msg = Msg("Finished Themes Insertion")
                )
                // ===============================================================================//

                // ================================Insertion======================================//
                logDebug(
                    source = source,
                    msg = Msg("Starting Demographics Insertion")
                )
                localDataSource.insertDemographics(
                    demographics
                        .asSequence()
                        .map(AnimeDemographicDto::asDemographic)
                        .map(AnimeDemographic::asEntity)
                        .toList()
                )
                logDebug(
                    source = source,
                    msg = Msg("Finished Demographics Insertion")
                )
                // ===============================================================================//

                //  Short delay to avoid API Limit
                delay(1500)

                //  Final Emission
                logDebug(
                    source = source,
                    msg = Msg("Finished every process, starting emission")
                )
                emit(UiState.Success(true))
            }
        }.onStart {
            emit(UiState.Loading)
        }.catch { t ->
            with(loggerApi) {
                logDebug(
                    source = source,
                    msg = Msg(t.stackTraceToString())
                )
                emit(UiState.Failed(UiText.MessageString(t.message.orEmpty())))
            }
        }
    }

    override fun insertAnime(anime: Anime): Flow<UiState<Boolean>> {
        return flow<UiState<Boolean>> {
            with(loggerApi) {

                logDebug(
                    source = source,
                    msg = Msg("Anime insertion started")
                )

                val duration = measureTime {
                    localDataSource.insertAnime(anime.asNewEntity())
                }

                logDebug(
                    source = source,
                    msg = Msg("Anime insertion finished within ${duration.inWholeMilliseconds}ms")
                )

                emit(UiState.Success(true))
            }
        }.onStart {
            emit(UiState.Loading)
        }.catch { t ->
            with(loggerApi) {
                logDebug(
                    source = source,
                    msg = Msg(t.stackTraceToString())
                )
                emit(UiState.Failed(UiText.MessageString(t.message.orEmpty())))
            }
        }
    }

    override fun getAnimeByAnimeID(animeID: Int): Flow<UiState<Anime>> {
        return flow<UiState<Anime>> {
            val animeEntity: AnimeEntity = localDataSource.getAnimeByAnimeID(animeID)
            val anime: Anime = animeEntity.asAnime()
            emit(UiState.Success(data = anime))
        }.onStart {

        }.catch { t ->
            with(loggerApi) {
                logDebug(
                    source = source,
                    msg = Msg(t.stackTraceToString())
                )
                emit(UiState.Failed(UiText.MessageString(t.message.orEmpty())))
            }
        }
    }

    override fun getAnimeHistory(): Flow<PagingData<Anime>> {
        return localDataSource.getAnimeHistory().map { pagingData ->
            pagingData.map { entity ->
                entity.asAnime()
            }
        }
    }

    override suspend fun insertWatchList(animeID: Int) {
        localDataSource.insertWatchList(animeID)
    }

    override fun getAnimeWatchList(): Flow<PagingData<Anime>> {
        return localDataSource.getAnimeWatchList().map { pagingData ->
            pagingData.map { entity ->
                entity.asAnime()
            }
        }
    }

    override fun getWatchListByAnimeID(animeID: Int): Flow<Boolean> {
        return localDataSource.getWatchListByAnimeID(animeID)
    }
}