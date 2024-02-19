package com.lelestacia.hayate.feature.anime.detail.domain.usecases

import com.lelestacia.hayate.core.common.state.UiState
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailAnimeUseCases @Inject constructor(
    private val repository: AnimeRepository,
) {

    fun getAnimeByAnimeID(animeID: Int): Flow<UiState<Anime>> {
        return repository.getAnimeByAnimeID(animeID)
    }

    suspend fun insertWatchList(animeID: Int) {
        repository.insertWatchList(animeID)
    }

    fun getWatchListByAnimeID(animeID: Int): Flow<Boolean> {
        return repository.getWatchListByAnimeID(animeID)
    }

    fun insertAnime(anime: Anime): Flow<UiState<Boolean>> {
        return repository.insertAnime(anime)
    }
}