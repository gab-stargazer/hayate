package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.common.shared.BaseViewModel
import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.airing.AiringAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.airing.AiringAnimeState
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import com.lelestacia.hayate.feature.anime.shared.AnimeType
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
class AiringAnimeViewModel @Inject constructor(
    private val animeUseCases: AnimeUseCases
) : BaseViewModel() {

    private val animeType: MutableStateFlow<AnimeType?> = MutableStateFlow(null)

    private val _state: MutableStateFlow<AiringAnimeState> = MutableStateFlow(AiringAnimeState())
    val state: StateFlow<AiringAnimeState> = _state.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val airingAnime: Flow<PagingData<Anime>> = animeType.flatMapLatest { currentType ->
        animeUseCases.getCurrentSeasonAnime(
            filter = currentType?.name?.lowercase()
        )
    }.cachedIn(viewModelScope)

    fun onEvent(event: AiringAnimeEvent) = viewModelScope.launch {
        when (event) {
            is AiringAnimeEvent.OnAnimeClicked -> {
                //  TODO: IMPLEMENT ROUTE CHANGES HERE
            }

            is AiringAnimeEvent.OnAnimeFilterChanged -> {
                animeType.update {
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