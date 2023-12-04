package com.lelestacia.hayate.feature.anime.exploration.ui.di

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lelestacia.hayate.common.shared.api.FeatureApi
import javax.inject.Inject

interface ExploreModuleAPI : FeatureApi

class ExploreModuleApiImpl @Inject constructor() : ExploreModuleAPI {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState
    ) {
        InternalExploreModuleAPI.registerGraph(
            navController = navController,
            navGraphBuilder = navGraphBuilder,
            snackBarHostState = snackBarHostState
        )
    }
}