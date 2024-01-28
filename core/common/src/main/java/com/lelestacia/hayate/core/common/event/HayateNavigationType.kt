package com.lelestacia.hayate.core.common.event

import androidx.annotation.StringRes
import androidx.navigation.NavOptions
import com.lelestacia.hayate.core.common.event.HayateNavigationType.Navigate
import com.lelestacia.hayate.core.common.event.HayateNavigationType.PopBackstack

/**
 * Represents the different types of navigation actions for the Hayate application.
 * This sealed class is designed to encapsulate various navigation operations, providing a
 * clear and structured way to handle navigation events within the application.
 *
 * The sealed class has two possible navigation types:
 * - [Navigate]: Represents a navigation action to a specific route with optional navigation options.
 * - [PopBackstack]: Represents a navigation action to pop the back stack, typically used to navigate
 *   back to the previous screen or fragment.
 * @see NavOptions
 * @author Kamil Malik
 * @since 23 January 2024
 */
sealed class HayateNavigationType {

    /**
     * Represents a navigation action to a specific route with optional navigation options.
     * @property route The destination route to navigate to.
     * @property options Optional navigation options to customize the navigation behavior.
     */
    data class Navigate(
        val route: String,
        val options: NavOptions?,
    ) : HayateNavigationType()

    data class NavigateWithTitle(
        @StringRes val title: Int,
        val route: String,
        val options: NavOptions?,
    ) : HayateNavigationType()

    /**
     * Represents a navigation action to pop the back stack, typically used to navigate
     * back to the previous screen or fragment.
     */
    data object PopBackstack : HayateNavigationType()

    data object PopBackstackFromTitle : HayateNavigationType()
}


