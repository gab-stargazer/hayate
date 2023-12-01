package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.airing

import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
import com.lelestacia.hayate.feature.anime.shared.AnimeTypeFilter

sealed class AiringAnimeEvent {
    data class OnAnimeClicked(val anime: Anime) : AiringAnimeEvent()
    data class OnAnimeFilterChanged(val filter: AnimeTypeFilter?) : AiringAnimeEvent()
    data object OnBottomSheetToggled : AiringAnimeEvent()
    data object OnTypeFilterMenuToggled : AiringAnimeEvent()
}