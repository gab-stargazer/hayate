package com.lelestacia.hayate.feature.anime.exploration.domain.repository

import androidx.paging.PagingData
import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
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
}