package com.lelestacia.hayate.feature.anime.initialization.domain.state

import com.lelestacia.hayate.core.common.state.UiState

data class InitializationScreenState(
    val initializationResult: UiState<Boolean> = UiState.None,
)
