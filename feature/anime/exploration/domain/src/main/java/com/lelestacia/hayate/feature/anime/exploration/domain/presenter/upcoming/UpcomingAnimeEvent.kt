package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.upcoming

import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
import com.lelestacia.hayate.feature.anime.shared.AnimeTypeFilter

sealed class UpcomingAnimeEvent {
    data class OnAnimeClicked(val anime: Anime) : UpcomingAnimeEvent()
    data class OnAnimeFilterChanged(val filter: AnimeTypeFilter?) : UpcomingAnimeEvent()
    data object OnBottomSheetToggled : UpcomingAnimeEvent()
    data object OnTypeFilterMenuToggled : UpcomingAnimeEvent()
}