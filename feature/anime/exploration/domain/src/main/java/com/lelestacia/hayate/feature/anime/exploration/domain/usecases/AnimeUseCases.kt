package com.lelestacia.hayate.feature.anime.exploration.domain.usecases

import androidx.paging.PagingData
import com.lelestacia.hayate.core.common.state.DataState
import com.lelestacia.hayate.core.preferences.ConfigPreferences
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeUseCases @Inject constructor(
    private val repository: AnimeRepository,
    private val preferences: ConfigPreferences
) {

    fun getTopAnime(
        type: String? = null,
        filter: String? = null,
        rating: String? = null,
    ): Flow<PagingData<Anime>> {
        return repository.getTopAnime(
            type = type,
            filter = filter,
            rating = rating
        )
    }

    fun getCurrentSeasonAnime(
        filter: String? = null,
    ): Flow<PagingData<Anime>> {
        return repository.getCurrentSeasonAnime(filter)
    }

    fun getUpcomingSeasonAnime(
        filter: String? = null,
    ): Flow<PagingData<Anime>> {
        return repository.getUpcomingSeasonAnime(filter)
    }

    fun getScheduledAnime(
        filter: String? = null,
    ): Flow<PagingData<Anime>> {
        return repository.getScheduledAnime(filter)
    }

    fun insertAnime(anime: Anime): Flow<DataState<Boolean>> {
        return repository.insertAnime(anime)
    }

    fun isFeatureEnabled(key: String): Flow<Boolean> {
        return preferences.isFeatureEnabled(key)
    }
}