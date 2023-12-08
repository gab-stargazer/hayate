package com.lelestacia.hayate.common.shared.event

sealed class HayateEvent {
    data class OnDarkThemeChanged(val isDarkTheme: Boolean) : HayateEvent()
    data class OnDestinationChanged(val route: String) : HayateEvent()
    data class OnDestinationChangedWithTitle(val route: String, val title: String) : HayateEvent()
}