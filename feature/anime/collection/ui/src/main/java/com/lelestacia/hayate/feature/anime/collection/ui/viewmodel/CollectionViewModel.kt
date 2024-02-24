package com.lelestacia.hayate.feature.anime.collection.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.core.common.state.UiState
import com.lelestacia.hayate.core.common.util.UiText
import com.lelestacia.hayate.feature.anime.collection.domain.usecases.AnimeCollectionUseCases
import com.lelestacia.hayate.feature.anime.collection.ui.presenter.CollectionState
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val useCases: AnimeCollectionUseCases,
) : ViewModel() {

    val animeHistory: Flow<PagingData<Anime>> = useCases
        .getAnimeHistory()
        .cachedIn(viewModelScope)

    val animeWatchList: Flow<PagingData<Anime>> = useCases
        .getAnimeWatchList()
        .cachedIn(viewModelScope)

    private val _collectionScreenState: MutableStateFlow<CollectionState> =
        MutableStateFlow(CollectionState())
    val state: StateFlow<CollectionState> =
        _collectionScreenState.asStateFlow()

    fun insertAnime(anime: Anime) = viewModelScope.launch {
        useCases.insertAnime(anime).collectLatest { state ->
            when (state) {
                is UiState.Failed -> Timber.e("Inserting Anime Failed. Reason: ${(state.error as UiText.MessageString).message}")
                UiState.Loading -> Timber.d("Inserting Anime into DB")
                UiState.None -> Unit
                is UiState.Success -> Timber.d("Anime insertion completed")
            }
        }
    }
}