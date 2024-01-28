package com.lelestacia.hayate.feature.anime.search.ui.presenter

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeRating
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType

data class SearchState(
    val animeType: AnimeType? = null,
    val animeRating: AnimeRating? = null,
    val gridState: LazyGridState = LazyGridState(),
    val listState: LazyListState = LazyListState(),
    val isAnimeTypeMenuOpened: Boolean = false,
    val isAnimeRatingMenuOpened: Boolean = false,
)
