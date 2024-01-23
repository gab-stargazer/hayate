package com.lelestacia.hayate.feature.anime.exploration.ui.presenter.airing

import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType


internal sealed class AiringAnimeEvent {
    data class OnAnimeFilterChanged(val filter: AnimeType?) : AiringAnimeEvent()
    data object OnBottomSheetToggled : AiringAnimeEvent()
    data object OnTypeFilterMenuToggled : AiringAnimeEvent()
}