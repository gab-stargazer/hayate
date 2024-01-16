package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import com.lelestacia.hayate.feature.anime.exploration.ui.Constant.AIRING_ANIME_TYPE_KEY
import com.lelestacia.hayate.feature.anime.exploration.ui.presenter.airing.AiringAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.ui.presenter.airing.AiringAnimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AiringViewModel @Inject constructor(
    private val animeUseCases: AnimeUseCases,
    private val savedStateHandle: SavedStateHandle
) : BaseExploreViewModel(animeUseCases) {

    private val animeType: StateFlow<AnimeType?> = savedStateHandle
        .getStateFlow(
            key = AIRING_ANIME_TYPE_KEY,
            initialValue = null
        )

    private val _state: MutableStateFlow<AiringAnimeState> = MutableStateFlow(
        AiringAnimeState(
            animeType = savedStateHandle[AIRING_ANIME_TYPE_KEY]
        )
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val airingAnime: Flow<PagingData<Anime>> = animeType.flatMapLatest { currentType ->
        animeUseCases.getCurrentSeasonAnime(
            filter = currentType?.name?.lowercase()
        )
    }.cachedIn(viewModelScope)

    val state: StateFlow<AiringAnimeState> = combine(
        _state,
        airingAnime
    ) { state, anime ->
        AiringAnimeState(
            anime = anime,
            animeType = state.animeType,
            gridState = state.gridState,
            listState = state.listState,
            isBottomSheetOpened = state.isBottomSheetOpened,
            isTypeMenuOpened = state.isTypeMenuOpened
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(60000),
        initialValue = AiringAnimeState()
    )

    fun onEvent(event: AiringAnimeEvent) = viewModelScope.launch {
        when (event) {
            is AiringAnimeEvent.OnAnimeFilterChanged -> {
                savedStateHandle[AIRING_ANIME_TYPE_KEY] = event.filter

                _state.update {
                    it.gridState.scrollToItem(0)

                    it.copy(
                        animeType = event.filter
                    )
                }
            }

            AiringAnimeEvent.OnBottomSheetToggled -> {
                _state.update { currentState ->
                    currentState.copy(
                        isBottomSheetOpened = !currentState.isBottomSheetOpened
                    )
                }
            }

            AiringAnimeEvent.OnTypeFilterMenuToggled -> {
                _state.update { currentState ->
                    currentState.copy(
                        isTypeMenuOpened = !currentState.isTypeMenuOpened
                    )
                }
            }
        }
    }
}