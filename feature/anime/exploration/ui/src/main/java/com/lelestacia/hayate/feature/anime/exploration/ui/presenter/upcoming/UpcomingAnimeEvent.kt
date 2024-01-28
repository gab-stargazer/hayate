package com.lelestacia.hayate.feature.anime.exploration.ui.presenter.upcoming

import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType

internal sealed class UpcomingAnimeEvent {
    data class OnAnimeFilterChanged(val filter: AnimeType?) : UpcomingAnimeEvent()
    data object OnBottomSheetToggled : UpcomingAnimeEvent()
    data object OnTypeFilterMenuToggled : UpcomingAnimeEvent()
}