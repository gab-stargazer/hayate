package com.lelestacia.hayate.util

import com.lelestacia.hayate.common.shared.Screen

fun shouldAppBarBeVisible(route: String): Boolean {
    val rootDestination = listOf(
        Screen.Exploration.route,
        Screen.Collection.route,
        Screen.More.route
    )

    val initializationScreen = Screen.Init.route
    val detailScreen = Screen.Detail.route
    val searchScreen = Screen.Search.route

    return when {
        rootDestination.contains(route) -> true
        initializationScreen.contains(route) -> false
        detailScreen.contains(route) -> true
        searchScreen.contains(route) -> true
        else -> false
    }
}

fun shouldSearchIconBeVisible(route: String): Boolean {
    return route == Screen.Exploration.route || route == Screen.Search.route
}