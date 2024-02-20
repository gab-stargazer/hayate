package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import com.lelestacia.hayate.feature.anime.exploration.ui.Constant.FIREBASE_RC_FEATURE_DAILY
import com.lelestacia.hayate.feature.anime.exploration.ui.presenter.schedule.ScheduleAnimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ScheduleViewModel @Inject constructor(
    private val animeUseCases: AnimeUseCases,
) : BaseExploreViewModel(animeUseCases) {

    private val _state: MutableStateFlow<ScheduleAnimeState> =
        MutableStateFlow(ScheduleAnimeState())
    val state: StateFlow<ScheduleAnimeState> =
        _state.asStateFlow()

    val isFeatureEnabled = animeUseCases.isFeatureEnabled(FIREBASE_RC_FEATURE_DAILY)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = true
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val scheduledAnime = state.flatMapLatest { currentState ->
        animeUseCases.getScheduledAnime(
            filter = currentState.currentDay
        )
    }.cachedIn(viewModelScope)
}