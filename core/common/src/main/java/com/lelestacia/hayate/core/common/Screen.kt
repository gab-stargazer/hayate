package com.lelestacia.hayate.core.common

sealed class Screen(val route: String) {
    data object Init : Screen("init")
    data object Exploration : Screen("explore")
    data object Collection : Screen("collection")
    data object More : Screen("more")
    data object Detail : Screen("anime/{data}") {
        fun createRoute(jsonAnime: String): String {
            return this.route.replace(
                oldValue = "{data}",
                newValue = jsonAnime
            )
        }
    }

    data object Search : Screen("search")
}