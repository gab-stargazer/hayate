package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lelestacia.hayate.common.shared.BaseViewModel
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.schedule.ScheduleAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.schedule.ScheduleAnimeState
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
internal class ScheduleViewModel @Inject constructor(
    private val animeUseCases: AnimeUseCases
) : BaseViewModel() {

    private val _state: MutableStateFlow<ScheduleAnimeState> =
        MutableStateFlow(ScheduleAnimeState())
    val state: StateFlow<ScheduleAnimeState> =
        _state.asStateFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    val scheduledAnime = state.flatMapLatest { currentState ->
        animeUseCases.getScheduledAnime(
            filter = currentState.currentDay
        )
    }.cachedIn(viewModelScope)

    fun onEvent(event: ScheduleAnimeEvent) {
        TODO("Implement it later if there is requirement")
    }
}