package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import com.lelestacia.hayate.feature.anime.shared.AnimeFilter
import com.lelestacia.hayate.feature.anime.shared.AnimeType

data class PopularAnimeState(
    val animeType: AnimeType? = null,
    val animeFilter: AnimeFilter? = null,
    val gridState: LazyGridState = LazyGridState(),
    val listState: LazyListState = LazyListState(),
    val isBottomSheetOpened: Boolean = false,
    val isTypeMenuOpened: Boolean = false,
    val isFilterMenuOpened: Boolean = false
)