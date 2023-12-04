package com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular

import com.lelestacia.hayate.feature.anime.shared.filter.AnimeFilter
import com.lelestacia.hayate.feature.anime.shared.filter.AnimeRating
import com.lelestacia.hayate.feature.anime.shared.filter.AnimeType

sealed class PopularAnimeEvent {
    data class OnAnimeTypeChanged(val type: AnimeType?) : PopularAnimeEvent()
    data object OnBottomSheetToggled : PopularAnimeEvent()
    data object OnAnimeTypeMenuToggled : PopularAnimeEvent()
    data class OnAnimeFilterChanged(val filter: AnimeFilter?) : PopularAnimeEvent()
    data object OnAnimeFilterMenuToggled : PopularAnimeEvent()
    data class OnAnimeRatingChanged(val rating: AnimeRating?) : PopularAnimeEvent()
    data object OnAnimeRatingMenuToggled : PopularAnimeEvent()
}