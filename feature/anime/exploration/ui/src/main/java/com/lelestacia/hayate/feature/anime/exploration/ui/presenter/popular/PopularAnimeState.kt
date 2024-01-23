package com.lelestacia.hayate.feature.anime.exploration.ui.presenter.popular

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Immutable
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeFilter
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeRating
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType

@Immutable
internal data class PopularAnimeState(
    val animeType: AnimeType? = null,
    val animeFilter: AnimeFilter? = null,
    val animeRating: AnimeRating? = null,
    val gridState: LazyGridState = LazyGridState(),
    val listState: LazyListState = LazyListState(),
    val isBottomSheetOpened: Boolean = false,
    val isAnimeTypeMenuOpened: Boolean = false,
    val isAnimeFilterMenuOpened: Boolean = false,
    val isAnimeRatingMenuOpened: Boolean = false,
)