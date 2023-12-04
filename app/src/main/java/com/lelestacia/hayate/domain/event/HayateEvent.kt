package com.lelestacia.hayate.domain.event

sealed class HayateEvent {
    data class OnDarkThemeChanged(val isDarkTheme: Boolean) : HayateEvent()
    data class OnDestinationChanged(val route: String) : HayateEvent()
}