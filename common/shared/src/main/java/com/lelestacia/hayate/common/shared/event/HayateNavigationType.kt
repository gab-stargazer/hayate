package com.lelestacia.hayate.common.shared.event

import androidx.navigation.NavOptions

sealed class HayateNavigationType {

    data class Navigate(
        val route: String,
        val options: NavOptions?,
    ) : HayateNavigationType()

    data object PopBackstack : HayateNavigationType()
}

