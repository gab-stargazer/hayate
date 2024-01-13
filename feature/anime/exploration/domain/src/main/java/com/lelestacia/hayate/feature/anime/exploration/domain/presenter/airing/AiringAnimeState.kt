package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.airing

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime

@Immutable
data class AiringAnimeState(
    val anime: PagingData<Anime> = PagingData.empty(),
    val animeType: AnimeType? = null,
    val gridState: LazyGridState = LazyGridState(),
    val listState: LazyListState = LazyListState(),
    val isBottomSheetOpened: Boolean = false,
    val isTypeMenuOpened: Boolean = false,
)
