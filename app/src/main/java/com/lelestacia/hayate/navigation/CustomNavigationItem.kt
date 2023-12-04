package com.lelestacia.hayate.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.ui.graphics.vector.ImageVector
import com.lelestacia.hayate.R
import com.lelestacia.hayate.common.shared.Screen

data class CustomNavigationItem(
    @StringRes val title: Int,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val HayateNavigationItem = listOf(
    CustomNavigationItem(
        title = R.string.exploration,
        route = Screen.Exploration.route,
        selectedIcon = Icons.Filled.Explore,
        unselectedIcon = Icons.Outlined.Explore
    ),
    CustomNavigationItem(
        title = R.string.collection,
        route = Screen.Collection.route,
        selectedIcon = Icons.Filled.CollectionsBookmark,
        unselectedIcon = Icons.Outlined.CollectionsBookmark
    ),
    CustomNavigationItem(
        title = R.string.more,
        route = Screen.More.route,
        selectedIcon = Icons.Filled.MoreHoriz,
        unselectedIcon = Icons.Outlined.MoreHoriz
    ),
)

fun isRootDestination(route: String): Boolean {
    val rootDestination = listOf(
        Screen.Exploration.route,
        Screen.Collection.route,
        Screen.More.route
    )

    return rootDestination.contains(route)
}