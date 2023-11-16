package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
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
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeUseCases: AnimeUseCases
) : ViewModel() {

    val scheduleAnimeFilter: MutableStateFlow<String> = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val scheduleAnime: Flow<PagingData<Anime>> = scheduleAnimeFilter
        .flatMapLatest {
            animeUseCases.getScheduledAnime(it)
        }.cachedIn(viewModelScope)

    private val _airingAnimeType: MutableStateFlow<AnimeTypeFilter?> = MutableStateFlow(null)
    val airingAnimeType: StateFlow<AnimeTypeFilter?> = _airingAnimeType.asStateFlow()

    val topAnime: Flow<PagingData<Anime>> = animeUseCases
        .getTopAnime()
        .cachedIn(viewModelScope)

    @OptIn(ExperimentalCoroutinesApi::class)
    val currentSeasonAnime = _airingAnimeType.flatMapLatest {
        animeUseCases
            .getCurrentSeasonAnime(
                it?.name?.lowercase()
            )
            .cachedIn(viewModelScope)
    }

    val upcomingAnime = animeUseCases
        .getUpcomingSeasonAnime()
        .cachedIn(viewModelScope)

    val topAnimeState = LazyGridState()
    val currentSeasonAnimeState = LazyGridState()
    val upcomingAnimeState = LazyGridState()
    val scheduleAnimeState = LazyGridState()

    fun onEvent(event: AnimeScreenEvent) {
        when (event) {
            is AnimeScreenEvent.AiringAnimeEvent.OnTypeChanged -> {
                _airingAnimeType.update {
                    event.filter
                }
            }
        }
    }
}

sealed class AnimeScreenEvent {
    sealed class AiringAnimeEvent : AnimeScreenEvent() {
        data class OnTypeChanged(val filter: AnimeTypeFilter?) : AiringAnimeEvent()
    }
}