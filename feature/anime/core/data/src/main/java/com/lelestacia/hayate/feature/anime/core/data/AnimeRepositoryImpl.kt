package com.lelestacia.hayate.feature.anime.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.lelestacia.hayate.common.shared.DataState
import com.lelestacia.hayate.common.shared.util.UiText
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
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.measureTime

internal class AnimeRepositoryImpl @Inject constructor(
    private val remoteDataSource: AnimeRemoteDataSourceApi,
    private val localDataSource: AnimeLocalDataSourceApi
) : AnimeRepository {

    override fun getTopAnime(
        type: String?,
        filter: String?,
        rating: String?
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
        sfw: Boolean
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
        sfw: Boolean
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
        sfw: Boolean
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

    override fun initiateApp(): Flow<DataState<Boolean>> {
        return flow<DataState<Boolean>> {

            val genres = remoteDataSource.getGenres()
            val explicitGenres = remoteDataSource.getExplicitGenres()

            localDataSource.insertGenres(
                genres
                    .asSequence()
                    .map(AnimeGenreDto::asGenre)
                    .map(AnimeGenre::asEntity)
                    .toList()
            )

            localDataSource.insertExplicitGenres(
                explicitGenres
                    .asSequence()
                    .map(AnimeGenreDto::asGenre)
                    .map(AnimeGenre::asExplicitEntity)
                    .toList()
            )

            delay(1500)

            val themes = remoteDataSource.getThemes()
            val demographics = remoteDataSource.getDemographics()

            localDataSource.insertThemes(
                themes
                    .asSequence()
                    .map(AnimeThemeDto::asTheme)
                    .map(AnimeTheme::asEntity)
                    .toList()
            )

            localDataSource.insertDemographics(
                demographics
                    .asSequence()
                    .map(AnimeDemographicDto::asDemographic)
                    .map(AnimeDemographic::asEntity)
                    .toList()
            )

            delay(1500)

            emit(DataState.Success(true))
        }.onStart {
            emit(DataState.Loading)
        }.catch { t ->
            Timber.e(t.stackTraceToString())
            emit(DataState.Failed(UiText.MessageString(t.message.orEmpty())))
        }
    }

    override suspend fun insertAnime(anime: Anime): Flow<DataState<Boolean>> {
        return flow<DataState<Boolean>> {
            val durations = measureTime {
                localDataSource.insertAnime(anime.asNewEntity())
            }
            Timber.d("Insert new Anime duration is: ${durations.inWholeMilliseconds}ms")

            emit(DataState.Success(true))
        }.onStart {
            emit(DataState.Loading)
        }.catch { t ->
            t.printStackTrace()
            emit(DataState.Failed(UiText.MessageString(t.message.orEmpty())))
        }
    }
}