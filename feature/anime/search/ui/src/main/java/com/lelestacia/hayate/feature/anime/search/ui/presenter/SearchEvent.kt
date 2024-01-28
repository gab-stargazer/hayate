package com.lelestacia.hayate.feature.anime.search.ui.presenter

import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeRating
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType

sealed class SearchEvent {
    data class OnAnimeTypeChanged(val type: AnimeType?) : SearchEvent()
    data object OnAnimeTypeMenuToggled : SearchEvent()
    data class OnAnimeRatingChanged(val rating: AnimeRating?) : SearchEvent()
    data object OnAnimeRatingMenuToggled : SearchEvent()
}