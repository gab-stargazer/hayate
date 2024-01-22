package com.lelestacia.hayate.feature.anime.initialization.ui.di

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import com.lelestacia.hayate.common.shared.api.FeatureApi
import com.lelestacia.hayate.common.shared.event.HayateEvent
import javax.inject.Inject

interface InitializationModuleAPI : FeatureApi

class InitializationModuleApiImpl @Inject constructor() : InitializationModuleAPI {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        InternalInitializationModuleAPI.registerGraph(
            navGraphBuilder = navGraphBuilder,
            snackBarHostState = snackBarHostState,
            onEvent = onEvent
        )
    }
}