package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.upcoming

import com.lelestacia.hayate.feature.anime.shared.filter.AnimeType

sealed class UpcomingAnimeEvent {
    data class OnAnimeFilterChanged(val filter: AnimeType?) : UpcomingAnimeEvent()
    data object OnBottomSheetToggled : UpcomingAnimeEvent()
    data object OnTypeFilterMenuToggled : UpcomingAnimeEvent()
}