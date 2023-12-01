package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.common.shared.BaseViewModel
import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.upcoming.UpcomingAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.upcoming.UpcomingAnimeState
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import com.lelestacia.hayate.feature.anime.shared.AnimeTypeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingAnimeViewModel @Inject constructor(
    private val animeUseCases: AnimeUseCases
) : BaseViewModel() {

    private val filterAnimeType: MutableStateFlow<AnimeTypeFilter?> = MutableStateFlow(null)

    private val _state: MutableStateFlow<UpcomingAnimeState> =
        MutableStateFlow(UpcomingAnimeState())
    val state: StateFlow<UpcomingAnimeState> = _state.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val upcomingAnime: Flow<PagingData<Anime>> = filterAnimeType.flatMapLatest { currentState ->
        animeUseCases.getUpcomingSeasonAnime(
            filter = currentState?.name?.lowercase()
        )
    }.cachedIn(viewModelScope)

    fun onEvent(event: UpcomingAnimeEvent) = viewModelScope.launch {
        when (event) {
            is UpcomingAnimeEvent.OnAnimeClicked -> {
                //  TODO: IMPLEMENT ROUTE CHANGES HERE
            }

            is UpcomingAnimeEvent.OnAnimeFilterChanged -> {
                filterAnimeType.update {
                    //  NOTE:
                    //  Theres no Need to copy and stuff since its only 1 value
                    event.filter
                }

                _state.update {
                    it.gridState.scrollToItem(0)

                    it.copy(
                        animeType = event.filter
                    )
                }
            }

            UpcomingAnimeEvent.OnBottomSheetToggled -> {
                _state.update { currentState ->
                    currentState.copy(
                        isBottomSheetOpened = !currentState.isBottomSheetOpened
                    )
                }
            }

            UpcomingAnimeEvent.OnTypeFilterMenuToggled -> {
                _state.update { currentState ->
                    currentState.copy(
                        isTypeFilterMenuOpened = !currentState.isTypeFilterMenuOpened
                    )
                }
            }
        }
    }
}