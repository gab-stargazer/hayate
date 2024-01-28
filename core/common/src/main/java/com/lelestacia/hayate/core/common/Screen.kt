package com.lelestacia.hayate.core.common

sealed class Screen(val route: String) {
    data object Init : Screen("init")
    data object Exploration : Screen("explore")
    data object Collection : Screen("collection")
    data object Detail : Screen("anime/{data}") {
        fun createRoute(jsonAnime: String): String {
            return this.route.replace(
                oldValue = "{data}",
                newValue = jsonAnime
            )
        }
    }

    data object Search : Screen("search")


    //  This is a navigation and not a composable route
    data object MoreNavigation : Screen("more")
    data object More : Screen("main")

    data object AppInfo : Screen("app_info")
}