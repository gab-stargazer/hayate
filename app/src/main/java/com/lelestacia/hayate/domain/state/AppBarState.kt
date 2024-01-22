package com.lelestacia.hayate.domain.state

import androidx.annotation.StringRes
import com.lelestacia.hayate.R
import com.lelestacia.hayate.common.shared.Screen

data class AppBarState(

    //  Current route, i don't want to pass navController around
    val currentRoute: String = Screen.Init.route,

    //  This part is for how the Appbar styled
    val isDarkTheme: Boolean = false,

    //  This part determined General Appbar Condition
    @StringRes val appBarTitle: Int = R.string.japanese_app_name,
    val shouldAppBarBeVisible: Boolean = false,
    val shouldNavigationIconBeVisible: Boolean = false,

    //  This part is for search query stuff
    val searchQuery: String = "",
    val isSearchModeActive: Boolean = false,
    val shouldSearchIconBeVisible: Boolean = false,

    //  This part is for Detail Screen
    val animeID: Int? = null,
    val trailerURL: String? = null,
)
