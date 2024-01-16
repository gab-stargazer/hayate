package com.lelestacia.hayate.feature.anime.collection.ui.presenter

import androidx.compose.foundation.lazy.grid.LazyGridState

data class CollectionState(
    val historyGridState: LazyGridState = LazyGridState(),
    val watchListGridState: LazyGridState = LazyGridState(),
)