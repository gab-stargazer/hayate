package com.lelestacia.hayate.feature.anime.core.source.local.api.api

import androidx.paging.PagingData
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.AnimeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.demographic.AnimeDemographicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeExplicitGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.theme.AnimeThemeEntity
import kotlinx.coroutines.flow.Flow

interface AnimeLocalDataSourceApi {
    suspend fun insertAnime(animeEntity: AnimeEntity)

    suspend fun insertGenres(genres: List<AnimeGenreEntity>)

    suspend fun insertExplicitGenres(explicitGenres: List<AnimeExplicitGenreEntity>)

    suspend fun insertThemes(themes: List<AnimeThemeEntity>)

    suspend fun insertDemographics(demographics: List<AnimeDemographicEntity>)

    suspend fun getAnimeByAnimeID(animeID: Int): AnimeEntity

    fun getAnimeHistory(): Flow<PagingData<AnimeEntity>>

    suspend fun insertWatchList(animeID: Int)

    fun getAnimeWatchList(): Flow<PagingData<AnimeEntity>>

    fun getWatchListByAnimeID(animeID: Int): Flow<Boolean>
}