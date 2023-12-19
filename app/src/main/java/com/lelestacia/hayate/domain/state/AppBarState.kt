package com.lelestacia.hayate.domain.state

import androidx.annotation.StringRes
import com.lelestacia.hayate.R

data class AppBarState(
    val isDarkTheme: Boolean = false,
    val shouldAppBarBeVisible: Boolean = false,
    val shouldNavigationIconBeVisible: Boolean = false,
    @StringRes val appBarTitle: Int = R.string.japanese_app_name
)
