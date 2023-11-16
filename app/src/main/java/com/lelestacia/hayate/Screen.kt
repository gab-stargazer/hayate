package com.lelestacia.hayate

sealed class Screen(val route: String) {
    data object Exploration : Screen("explore")
    data object Collection : Screen("collection")
    data object More : Screen("more")
}