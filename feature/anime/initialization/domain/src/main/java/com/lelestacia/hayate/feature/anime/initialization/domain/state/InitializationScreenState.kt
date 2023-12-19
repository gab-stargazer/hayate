package com.lelestacia.hayate.feature.anime.initialization.domain.state

import com.lelestacia.hayate.common.shared.DataState

data class InitializationScreenState(
    val initializationResult: DataState<Boolean> = DataState.None
)
