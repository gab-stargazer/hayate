package com.lelestacia.hayate.feature.anime.detail.ui.di

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lelestacia.hayate.common.shared.api.FeatureApi
import javax.inject.Inject

interface DetailModuleAPI : FeatureApi

class DetailModuleApiImpl @Inject constructor() : DetailModuleAPI {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState
    ) {
        InternalDetailModuleAPI.registerGraph(
            navController = navController,
            navGraphBuilder = navGraphBuilder,
            snackBarHostState = snackBarHostState
        )
    }
}