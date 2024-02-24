package com.lelestacia.hayate.core.common

/**
 * Represents a sealed class defining different screens and navigation routes within the Hayate application.
 * Each screen is associated with a unique route that can be used for navigation.
 *
 * @property route The route associated with the screen, used for navigation within the application.
 *
 * @see Init Represents the initial screen.
 * @see Exploration Represents the screen for exploring anime.
 * @see Collection Represents the screen for viewing a collection of anime.
 * @see Detail Represents the screen for detailed information about an anime, with a dynamic data parameter.
 * @see Search Represents the screen for searching anime.
 * @see MoreNavigation Represents a navigation point (not a composable route) to access more options.
 * @see MoreNavigation.More Represents the main screen with additional options.
 * @see MoreNavigation.AppInfo Represents the screen for displaying application information.
 *
 * with the provided JSON anime data dynamically.
 */
sealed class Screen(val route: String) {

    /**
     * Represents the initial screen.
     */
    data object Init : Screen("init")

    /**
     * Represents the screen for exploring content.
     */
    data object Exploration : Screen("explore")

    /**
     * Represents the screen for viewing a collection of anime.
     */
    data object Collection : Screen("collection")

    /**
     * Represents the screen for detailed information about an anime, with a dynamic data parameter.
     */
    data object Detail : Screen("anime/{data}") {

        /**
         * Creates a route for the Detail screen with the provided JSON anime data.
         *
         * @param jsonAnime The JSON string representing anime data.
         * @return The created route with the substituted anime data.
         */
        fun createRoute(jsonAnime: String): String {
            return this.route.replace(
                oldValue = "{data}",
                newValue = jsonAnime
            )
        }
    }

    /**
     * Represents the screen for searching anime.
     */
    data object Search : Screen("search")

    /**
     * Represents a navigation point (not a composable route) to access more options.
     */
    data object MoreNavigation : Screen("more") {

        /**
         * Represents the screen with additional options.
         */
        data object More : Screen("main")

        /**
         * Represents the screen for displaying application information.
         */
        data object AppInfo : Screen("app_info")
    }
}