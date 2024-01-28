package com.lelestacia.hayate.feature.anime.collection.domain.usecases

import androidx.paging.PagingData
import com.lelestacia.hayate.core.common.state.DataState
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeCollectionUseCases @Inject constructor(
    private val repository: AnimeRepository,
) {

    fun getAnimeHistory(): Flow<PagingData<Anime>> {
        return repository.getAnimeHistory()
    }

    fun getAnimeWatchList(): Flow<PagingData<Anime>> {
        return repository.getAnimeWatchList()
    }

    fun insertAnime(anime: Anime): Flow<DataState<Boolean>> {
        return repository.insertAnime(anime)
    }
}