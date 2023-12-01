package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.common.shared.BaseViewModel
import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular.PopularAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular.PopularAnimeState
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import com.lelestacia.hayate.feature.anime.shared.AnimeFilter
import com.lelestacia.hayate.feature.anime.shared.AnimeType
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
class PopularAnimeViewModel @Inject constructor(
    private val animeUseCases: AnimeUseCases
) : BaseViewModel() {

    private val animeType: MutableStateFlow<AnimeType?> = MutableStateFlow(null)
    private val animeFilter: MutableStateFlow<AnimeFilter?> = MutableStateFlow(null)

    private val filter: Flow<PopularAnimeFilter> =
        combine(animeType, animeFilter) { type: AnimeType?, filter: AnimeFilter? ->
            PopularAnimeFilter(
                type = type,
                filter = filter
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
            filter = currentFilter.filter?.query?.lowercase()
        )
    }.cachedIn(viewModelScope)

    fun onEvent(event: PopularAnimeEvent) = viewModelScope.launch {
        when (event) {
            is PopularAnimeEvent.OnAnimeClicked -> {
                //  TODO: IMPLEMENT ROUTE CHANGES HERE
            }

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

            PopularAnimeEvent.OnFilterMenuToggled -> {
                _state.update { currentState ->
                    currentState.copy(
                        isFilterMenuOpened = !currentState.isFilterMenuOpened
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

            PopularAnimeEvent.OnTypeMenuToggled -> {
                _state.update { currentState ->
                    currentState.copy(
                        isTypeMenuOpened = !currentState.isTypeMenuOpened
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
        }
    }

    data class PopularAnimeFilter(
        val type: AnimeType? = null,
        val filter: AnimeFilter? = null
    )
}