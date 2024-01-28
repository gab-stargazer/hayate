package com.lelestacia.hayate.feature.anime.detail.ui.presenter

import androidx.compose.runtime.Immutable
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime

@Immutable
data class DetailAnimeState(
    val anime: Anime? = null,
    val isOnWatchList: Boolean = false,
)
