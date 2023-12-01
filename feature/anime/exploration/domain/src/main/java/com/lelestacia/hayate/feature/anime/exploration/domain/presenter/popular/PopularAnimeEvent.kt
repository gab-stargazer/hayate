package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular

import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
import com.lelestacia.hayate.feature.anime.shared.AnimeFilter
import com.lelestacia.hayate.feature.anime.shared.AnimeType

sealed class PopularAnimeEvent {
    data class OnAnimeClicked(val anime: Anime) : PopularAnimeEvent()
    data class OnAnimeTypeChanged(val type: AnimeType?) : PopularAnimeEvent()
    data object OnBottomSheetToggled : PopularAnimeEvent()
    data object OnTypeMenuToggled : PopularAnimeEvent()
    data class OnAnimeFilterChanged(val filter: AnimeFilter?) : PopularAnimeEvent()
    data object OnFilterMenuToggled : PopularAnimeEvent()
}