package com.lelestacia.hayate.common.shared.event

import com.lelestacia.hayate.common.shared.util.UiText

sealed class HayateEvent {
    data class OnDarkThemeChanged(val isDarkTheme: Boolean) : HayateEvent()
    data class OnDestinationChanged(val route: String) : HayateEvent()
    data class OnDestinationChangedWithTitle(val route: String, val title: String) : HayateEvent()
    data class OnDetailAnimeToolbar(
        val animeID: Int?,
        val trailerURL: String?,
    ) : HayateEvent()

    data class ShowSnackBar(val message: UiText) : HayateEvent()

    data class OnNavigate(val navigation: HayateNavigationType) : HayateEvent()
}