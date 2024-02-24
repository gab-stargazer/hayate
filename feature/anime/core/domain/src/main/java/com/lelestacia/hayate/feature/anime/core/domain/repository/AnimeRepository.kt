package com.lelestacia.hayate.feature.anime.core.domain.repository

import androidx.paging.PagingData
import com.lelestacia.hayate.core.common.state.UiState
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    fun getTopAnime(
        type: String? = null,
        filter: String? = null,
        rating: String? = null,
    ): Flow<PagingData<Anime>>

    fun getCurrentSeasonAnime(
        filter: String? = null,
        sfw: Boolean = true,
    ): Flow<PagingData<Anime>>

    fun getUpcomingSeasonAnime(
        filter: String? = null,
        sfw: Boolean = true,
    ): Flow<PagingData<Anime>>

    fun getScheduledAnime(
        filter: String? = null,
        sfw: Boolean = true,
    ): Flow<PagingData<Anime>>

    fun getAnimeSearch(
        query: String = "",
        type: String? = null,
        rating: String? = null,
    ): Flow<PagingData<Anime>>

    fun initiateApp(): Flow<UiState<Boolean>>

    fun insertAnime(anime: Anime): Flow<UiState<Boolean>>

    fun getAnimeByAnimeID(animeID: Int): Flow<UiState<Anime>>

    fun getAnimeHistory(): Flow<PagingData<Anime>>

    suspend fun insertWatchList(animeID: Int)

    fun getAnimeWatchList(): Flow<PagingData<Anime>>
    fun getWatchListByAnimeID(animeID: Int): Flow<Boolean>
}