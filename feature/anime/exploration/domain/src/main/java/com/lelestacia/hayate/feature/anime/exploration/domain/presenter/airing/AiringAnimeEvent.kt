package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.airing

import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
import com.lelestacia.hayate.feature.anime.shared.AnimeType

sealed class AiringAnimeEvent {
    data class OnAnimeClicked(val anime: Anime) : AiringAnimeEvent()
    data class OnAnimeFilterChanged(val filter: AnimeType?) : AiringAnimeEvent()
    data object OnBottomSheetToggled : AiringAnimeEvent()
    data object OnTypeFilterMenuToggled : AiringAnimeEvent()
}