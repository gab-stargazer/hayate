package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.upcoming

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import com.lelestacia.hayate.feature.anime.shared.AnimeTypeFilter

data class UpcomingAnimeState(
    val animeType: AnimeTypeFilter? = null,
    val gridState: LazyGridState = LazyGridState(),
    val listState: LazyListState = LazyListState(),
    val isBottomSheetOpened: Boolean = false,
    val isTypeFilterMenuOpened: Boolean = false
)