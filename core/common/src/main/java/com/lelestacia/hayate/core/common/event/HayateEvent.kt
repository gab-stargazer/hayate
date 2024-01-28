package com.lelestacia.hayate.core.common.event

import com.lelestacia.hayate.core.common.util.UiText

/**
 * Represents various events that can occur within the Hayate application.
 * This sealed class encapsulates different types of user interactions and system events.
 *
 * @author Kamil Malik
 * @since 23 January 2024
 */
sealed class HayateEvent {

    /**
     * Event indicating a change in the dark theme setting.
     * @property isDarkTheme The new state of the dark theme.
     */
    data class DarkThemeChanged(val isDarkTheme: Boolean) : HayateEvent()

    /**
     * Event indicating a change in the destination route.
     * @property route The new destination route.
     */
    data class DestinationChanged(val route: String) : HayateEvent()

    /**
     * Event indicating details for an anime toolbar.
     * @property animeID The ID of the anime.
     * @property trailerURL The URL of the anime trailer.
     */
    data class OnDetailAnimeToolbar(
        val animeID: Int?,
        val trailerURL: String?,
    ) : HayateEvent()

    /**
     * Event to show a Snackbar with a specified message.
     * @property message The text content of the Snackbar.
     */
    data class ShowSnackBar(val message: UiText) : HayateEvent()

    /**
     * Event indicating a navigation action.
     * @property navigation The type of navigation action to perform.
     */
    data class Navigate(val navigation: HayateNavigationType) : HayateEvent()

    /**
     * Event indicating a toggle of search mode.
     */
    data object SearchModeToggle : HayateEvent()

    /**
     * Event indicating a change in the search query.
     * @property searchQuery The new search query.
     */
    data class SearchQueryChanged(val searchQuery: String) : HayateEvent()

    /**
     * Event indicating a click on the search button.
     */
    data object SearchClicked : HayateEvent()
}