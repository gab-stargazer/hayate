package com.lelestacia.hayate.feature.anime.search.domain.usecases

import androidx.paging.PagingData
import com.lelestacia.hayate.common.shared.DataState
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCases @Inject constructor(
    private val repository: AnimeRepository,
) {

    fun executeSearchAnime(
        query: String,
        type: String?,
        rating: String?,
    ): Flow<PagingData<Anime>> {
        return repository.getAnimeSearch(
            query = query,
            type = type,
            rating = rating
        )
    }

    fun insertAnime(anime: Anime): Flow<DataState<Boolean>> {
        return repository.insertAnime(anime)
    }
}