package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.schedule

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Immutable
import com.lelestacia.hayate.feature.anime.exploration.domain.util.getCurrentDay

@Immutable
data class ScheduleAnimeState(
    val currentDay: String = getCurrentDay(),
    val gridState: LazyGridState = LazyGridState(),
    val listState: LazyListState = LazyListState(),
)
