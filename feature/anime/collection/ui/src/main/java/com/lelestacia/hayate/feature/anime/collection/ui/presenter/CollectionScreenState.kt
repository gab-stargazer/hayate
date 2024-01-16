package com.lelestacia.hayate.feature.anime.collection.ui.presenter

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Immutable

@Immutable
data class CollectionScreenState(
    val gridState: LazyGridState = LazyGridState(),
)
