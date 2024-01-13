package com.lelestacia.hayate.common.shared.api

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lelestacia.hayate.common.shared.event.HayateEvent

/**
 * Interface FeatureApi defines methods to register a new feature as a screen in this app.
 */
interface FeatureApi {
    fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState,
        onEvent: (HayateEvent) -> Unit,
    )
}