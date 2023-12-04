package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.airing

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Immutable
import com.lelestacia.hayate.feature.anime.shared.filter.AnimeType

@Immutable
data class AiringAnimeState(
    val animeType: AnimeType? = null,
    val gridState: LazyGridState = LazyGridState(),
    val listState: LazyListState = LazyListState(),
    val isBottomSheetOpened: Boolean = false,
    val isTypeMenuOpened: Boolean = false
)
