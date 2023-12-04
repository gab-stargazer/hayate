package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.airing

import com.lelestacia.hayate.feature.anime.shared.filter.AnimeType

sealed class AiringAnimeEvent {
    data class OnAnimeFilterChanged(val filter: AnimeType?) : AiringAnimeEvent()
    data object OnBottomSheetToggled : AiringAnimeEvent()
    data object OnTypeFilterMenuToggled : AiringAnimeEvent()
}