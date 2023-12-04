package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.common.shared.BaseViewModel
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular.PopularAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular.PopularAnimeState
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import com.lelestacia.hayate.feature.anime.shared.filter.AnimeFilter
import com.lelestacia.hayate.feature.anime.shared.filter.AnimeRating
import com.lelestacia.hayate.feature.anime.shared.filter.AnimeType
import com.lelestacia.hayate.feature.anime.shared.model.Anime
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
    private val animeUseCases: AnimeUseCases
) : BaseViewModel() {

    private val animeType: MutableStateFlow<AnimeType?> = MutableStateFlow(null)
    private val animeFilter: MutableStateFlow<AnimeFilter?> = MutableStateFlow(null)
    private val animeRating: MutableStateFlow<AnimeRating?> = MutableStateFlow(null)

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
        MutableStateFlow(PopularAnimeState())
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
                animeFilter.update {
                    //  NOTE:
                    //  Theres no Need to copy and stuff since its only 1 value
                    event.filter
                }

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
                animeType.update {
                    //  NOTE:
                    //  Theres no Need to copy and stuff since its only 1 value
                    event.type
                }

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
                animeRating.update {
                    //  NOTE:
                    //  Theres no Need to copy and stuff since its only 1 value
                    event.rating
                }

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