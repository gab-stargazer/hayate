package com.lelestacia.hayate.util

import com.lelestacia.hayate.core.common.Screen

/**
 * Determines whether the app bar should be visible based on the provided [route].
 *
 * This function is used to control the visibility of the app bar for different screens within
 * the Hayate application. It returns `true` if the screen is one of the root destinations
 * (Exploration, Collection, More), detail screen, or search screen. Otherwise, it returns `false`.
 *
 * @param route The route of the current screen.
 * @return `true` if the app bar should be visible, `false` otherwise.
 * @see Screen
 */
fun shouldAppBarBeVisible(route: String): Boolean {
    val rootDestination = listOf(
        Screen.Exploration.route,
        Screen.Collection.route,
        Screen.MoreNavigation.More.route
    )

    val initializationScreen = Screen.Init.route
    val detailScreen = Screen.Detail.route
    val searchScreen = Screen.Search.route
    val appInfoScreen = Screen.MoreNavigation.AppInfo.route

    return when {
        rootDestination.contains(route) -> true
        initializationScreen.contains(route) -> false
        detailScreen.contains(route) -> true
        searchScreen.contains(route) -> true
        appInfoScreen.contains(route) -> true
        else -> false
    }
}

/**
 * Determines whether the search icon in the app bar should be visible based on the provided [route].
 *
 * This function is used to control the visibility of the search icon in the app bar for different
 * screens within the Hayate application. It returns `true` if the screen is the Exploration or
 * Search screen. Otherwise, it returns `false`.
 *
 * @param route The route of the current screen.
 * @return `true` if the search icon should be visible, `false` otherwise.
 * @see Screen
 */
fun shouldSearchIconBeVisible(route: String): Boolean {
    return route == Screen.Exploration.route || route == Screen.Search.route
}