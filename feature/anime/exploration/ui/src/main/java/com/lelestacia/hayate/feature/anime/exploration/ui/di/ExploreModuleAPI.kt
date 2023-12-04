package com.lelestacia.hayate.feature.anime.exploration.ui.di

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lelestacia.hayate.common.shared.api.FeatureApi
import javax.inject.Inject

interface ExploreModuleAPI : FeatureApi

class ExploreModuleApiImpl @Inject constructor() : ExploreModuleAPI {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        InternalExploreModuleAPI.registerGraph(
            navController = navController,
            navGraphBuilder = navGraphBuilder,
            modifier = modifier
        )
    }
}