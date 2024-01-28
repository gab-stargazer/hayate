package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import com.lelestacia.hayate.feature.anime.exploration.ui.Constant.FIREBASE_RC_FEATURE_UPCOMING
import com.lelestacia.hayate.feature.anime.exploration.ui.Constant.UPCOMING_ANIME_TYPE_KEY
import com.lelestacia.hayate.feature.anime.exploration.ui.presenter.upcoming.UpcomingAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.ui.presenter.upcoming.UpcomingAnimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UpcomingViewModel @Inject constructor(
    private val animeUseCases: AnimeUseCases,
    private val savedStateHandle: SavedStateHandle
) : BaseExploreViewModel(animeUseCases) {

    val isFeatureEnabled = animeUseCases.isFeatureEnabled(FIREBASE_RC_FEATURE_UPCOMING)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = true
        )

    private val animeType: StateFlow<AnimeType?> = savedStateHandle
        .getStateFlow(
            key = UPCOMING_ANIME_TYPE_KEY,
            initialValue = null
        )

    private val _state: MutableStateFlow<UpcomingAnimeState> =
        MutableStateFlow(
            UpcomingAnimeState(
                animeType = savedStateHandle[UPCOMING_ANIME_TYPE_KEY]
            )
        )
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
                savedStateHandle[UPCOMING_ANIME_TYPE_KEY] = event.filter

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