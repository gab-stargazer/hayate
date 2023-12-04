package com.lelestacia.hayate.common.shared.api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Interface FeatureApi defines methods to register a new feature as a screen in this app.
 */
interface FeatureApi {
    fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier = Modifier
    )
}