package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.common.shared.BaseViewModel
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeFilter
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeRating
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular.PopularAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular.PopularAnimeState
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import com.lelestacia.hayate.feature.anime.exploration.ui.Constant.POPULAR_ANIME_FILTER_KEY
import com.lelestacia.hayate.feature.anime.exploration.ui.Constant.POPULAR_ANIME_RATING_KEY
import com.lelestacia.hayate.feature.anime.exploration.ui.Constant.POPULAR_ANIME_TYPE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PopularViewModel @Inject constructor(
    private val animeUseCases: AnimeUseCases,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val animeType: StateFlow<AnimeType?> = savedStateHandle
        .getStateFlow(
            key = POPULAR_ANIME_TYPE_KEY,
            initialValue = null
        )
    private val animeFilter: StateFlow<AnimeFilter?> = savedStateHandle
        .getStateFlow(
            key = POPULAR_ANIME_FILTER_KEY,
            initialValue = null
        )
    private val animeRating: StateFlow<AnimeRating?> = savedStateHandle
        .getStateFlow(
            key = POPULAR_ANIME_RATING_KEY,
            initialValue = null
        )

    private val filter: Flow<PopularAnimeFilter> =
        combine(
            animeType,
            animeFilter,
            animeRating
        ) { type: AnimeType?, filter: AnimeFilter?, rating: AnimeRating? ->
            PopularAnimeFilter(
                type = type,
                filter = filter,
                rating = rating
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PopularAnimeFilter()
        )

    private val _state: MutableStateFlow<PopularAnimeState> =
        MutableStateFlow(
            PopularAnimeState(
                animeType = savedStateHandle[POPULAR_ANIME_TYPE_KEY],
                animeFilter = savedStateHandle[POPULAR_ANIME_FILTER_KEY],
                animeRating = savedStateHandle[POPULAR_ANIME_RATING_KEY]
            )
        )
    val state: StateFlow<PopularAnimeState> = _state.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val popularAnime: Flow<PagingData<Anime>> = filter.flatMapLatest { currentFilter ->
        animeUseCases.getTopAnime(
            type = currentFilter.type?.name?.lowercase(),
            filter = currentFilter.filter?.query?.lowercase(),
            rating = currentFilter.rating?.query?.lowercase()
        )
    }.cachedIn(viewModelScope)

    fun onEvent(event: PopularAnimeEvent) = viewModelScope.launch {
        when (event) {
            is PopularAnimeEvent.OnAnimeFilterChanged -> {
                savedStateHandle[POPULAR_ANIME_FILTER_KEY] = event.filter

                _state.update {
                    it.gridState.scrollToItem(0)

                    it.copy(
                        animeFilter = event.filter
                    )
                }
            }

            PopularAnimeEvent.OnAnimeFilterMenuToggled -> {
                _state.update { currentState ->
                    currentState.copy(
                        isAnimeFilterMenuOpened = !currentState.isAnimeFilterMenuOpened
                    )
                }
            }

            PopularAnimeEvent.OnBottomSheetToggled -> {
                _state.update { currentState ->
                    currentState.copy(
                        isBottomSheetOpened = !currentState.isBottomSheetOpened
                    )
                }
            }

            is PopularAnimeEvent.OnAnimeTypeChanged -> {
                savedStateHandle[POPULAR_ANIME_TYPE_KEY] = event.type

                _state.update {
                    it.gridState.scrollToItem(0)

                    it.copy(
                        animeType = event.type
                    )
                }
            }

            PopularAnimeEvent.OnAnimeTypeMenuToggled -> {
                _state.update { currentState ->
                    currentState.copy(
                        isAnimeTypeMenuOpened = !currentState.isAnimeTypeMenuOpened
                    )
                }
            }

            is PopularAnimeEvent.OnAnimeRatingChanged -> {
                savedStateHandle[POPULAR_ANIME_RATING_KEY] = event.rating

                _state.update {
                    it.gridState.scrollToItem(0)

                    it.copy(
                        animeRating = event.rating
                    )
                }
            }

            PopularAnimeEvent.OnAnimeRatingMenuToggled -> {
                _state.update { currentState ->
                    currentState.copy(
                        isAnimeRatingMenuOpened = !currentState.isAnimeRatingMenuOpened
                    )
                }
            }
        }
    }

    data class PopularAnimeFilter(
        val type: AnimeType? = null,
        val filter: AnimeFilter? = null,
        val rating: AnimeRating? = null
    )
}