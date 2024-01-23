package com.lelestacia.hayate.feature.anime.search.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.core.common.state.DataState
import com.lelestacia.hayate.core.common.util.UiText
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeRating
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.search.domain.usecases.SearchUseCases
import com.lelestacia.hayate.feature.anime.search.ui.presenter.SearchEvent
import com.lelestacia.hayate.feature.anime.search.ui.presenter.SearchState
import com.lelestacia.hayate.feature.anime.search.ui.util.SearchConstant.QUERY_SAVE_STATE_KEY
import com.lelestacia.hayate.feature.anime.search.ui.util.SearchConstant.RATING_SAVE_STATE_KEY
import com.lelestacia.hayate.feature.anime.search.ui.util.SearchConstant.TYPE_SAVE_STATE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: SearchUseCases,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val animeType: StateFlow<AnimeType?> = savedStateHandle
        .getStateFlow(
            key = TYPE_SAVE_STATE_KEY,
            initialValue = null
        )

    private val animeRating: StateFlow<AnimeRating?> = savedStateHandle
        .getStateFlow(
            key = RATING_SAVE_STATE_KEY,
            initialValue = null
        )

    private val searchQuery: StateFlow<String> = savedStateHandle
        .getStateFlow(
            key = QUERY_SAVE_STATE_KEY,
            initialValue = ""
        )

    private val _screenState: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    private val _queryState = combine(
        animeType,
        animeRating,
        searchQuery
    ) { type: AnimeType?, rating: AnimeRating?, query: String ->
        SearchQueryAndFilter(
            query = query,
            animeType = type,
            animeRating = rating
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = SearchQueryAndFilter()
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchAnime: Flow<PagingData<Anime>> = _queryState
        .flatMapLatest { state ->
            if (state.query.isBlank()) {
                flow {
                    emit(PagingData.empty())
                }
            } else {
                useCases.executeSearchAnime(
                    state.query,
                    state.animeType?.name?.lowercase(),
                    state.animeRating?.query?.lowercase()
                )
            }
        }.distinctUntilChanged()
        .cachedIn(viewModelScope)

    val screenState = combine(
        animeType,
        animeRating,
        _screenState
    ) { type: AnimeType?, rating: AnimeRating?, state: SearchState ->
        SearchState(
            animeType = type,
            isAnimeTypeMenuOpened = state.isAnimeTypeMenuOpened,
            animeRating = rating,
            isAnimeRatingMenuOpened = state.isAnimeRatingMenuOpened
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = SearchState()
    )

    fun onEvent(event: SearchEvent) = viewModelScope.launch {
        when (event) {
            is SearchEvent.OnAnimeRatingChanged -> {
                savedStateHandle[RATING_SAVE_STATE_KEY] = event.rating
            }

            SearchEvent.OnAnimeRatingMenuToggled -> {
                _screenState.update {
                    it.copy(
                        isAnimeRatingMenuOpened = !it.isAnimeRatingMenuOpened
                    )
                }
            }

            is SearchEvent.OnAnimeTypeChanged -> {
                savedStateHandle[TYPE_SAVE_STATE_KEY] = event.type
            }

            SearchEvent.OnAnimeTypeMenuToggled -> {
                _screenState.update {
                    it.copy(
                        isAnimeTypeMenuOpened = !it.isAnimeTypeMenuOpened
                    )
                }
            }
        }
    }

    fun updateSearchQuery(query: String) = viewModelScope.launch {
        savedStateHandle[QUERY_SAVE_STATE_KEY] = query
    }

    fun insertAnime(anime: Anime) = viewModelScope.launch {
        useCases.insertAnime(anime).collectLatest { state ->
            when (state) {
                is DataState.Failed -> Timber.e("Inserting Anime Failed. Reason: ${(state.error as UiText.MessageString).message}")
                DataState.Loading -> Timber.d("Inserting Anime into DB")
                DataState.None -> Unit
                is DataState.Success -> Timber.d("Anime insertion completed")
            }
        }
    }

    data class SearchQueryAndFilter(
        val query: String = "",
        val animeType: AnimeType? = null,
        val animeRating: AnimeRating? = null,
    )
}