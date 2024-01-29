package com.lelestacia.hayate.domain.state

import com.lelestacia.hayate.core.common.Screen
import com.lelestacia.hayate.navigation.CustomNavigationItem
import com.lelestacia.hayate.navigation.HayateNavigationItem

data class BottomNavigationState(
    val isDarkTheme: Boolean = false,
    val selectedRoute: String = Screen.Init.route,
    val isRootDestination: Boolean = false,
    val navigationItem: List<CustomNavigationItem> = HayateNavigationItem
)
