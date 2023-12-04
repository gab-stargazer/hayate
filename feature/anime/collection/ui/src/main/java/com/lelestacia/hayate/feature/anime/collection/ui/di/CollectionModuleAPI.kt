package com.lelestacia.hayate.feature.anime.collection.ui.di

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lelestacia.hayate.common.shared.api.FeatureApi
import javax.inject.Inject

interface CollectionModuleAPI : FeatureApi

class CollectionModuleApiImpl @Inject constructor() : CollectionModuleAPI {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState
    ) {
        InternalCollectionModuleAPI.registerGraph(
            navController = navController,
            navGraphBuilder = navGraphBuilder,
            snackBarHostState = snackBarHostState
        )
    }
}