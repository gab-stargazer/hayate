package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.common.shared.BaseViewModel
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.upcoming.UpcomingAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.upcoming.UpcomingAnimeState
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import com.lelestacia.hayate.feature.anime.shared.filter.AnimeType
import com.lelestacia.hayate.feature.anime.shared.model.Anime
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
internal class UpcomingViewModel @Inject constructor(
    private val animeUseCases: AnimeUseCases
) : BaseViewModel() {

    private val animeType: MutableStateFlow<AnimeType?> = MutableStateFlow(null)

    private val _state: MutableStateFlow<UpcomingAnimeState> =
        MutableStateFlow(UpcomingAnimeState())
    val state: StateFlow<UpcomingAnimeState> = _state.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val upcomingAnime: Flow<PagingData<Anime>> = animeType.flatMapLatest { currentType ->
        animeUseCases.getUpcomingSeasonAnime(
            filter = currentType?.name?.lowercase()
        )
    }.cachedIn(viewModelScope)

    fun onEvent(event: UpcomingAnimeEvent) = viewModelScope.launch {
        when (event) {
            is UpcomingAnimeEvent.OnAnimeFilterChanged -> {
                animeType.update {
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
                        isTypeMenuOpened = !currentState.isTypeMenuOpened
                    )
                }
            }
        }
    }
}