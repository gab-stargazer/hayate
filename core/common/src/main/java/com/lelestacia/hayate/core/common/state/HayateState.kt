package com.lelestacia.hayate.core.common.state

/**
 * Data class representing the state of the Hayate application.
 *
 * @param isDarkTheme Indicates whether the dark theme is enabled.
 * @param searchQuery The current search query in the application.
 *
 * @author Kamil Malik
 * @since 1 February 2024
 */
data class HayateState(
    val isDarkTheme: Boolean = false,
    val searchQuery: String = "",
)
