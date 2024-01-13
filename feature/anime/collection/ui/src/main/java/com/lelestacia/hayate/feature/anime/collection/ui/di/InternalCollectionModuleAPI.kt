package com.lelestacia.hayate.feature.anime.collection.ui.di

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.event.HayateEvent
import com.lelestacia.hayate.feature.anime.collection.ui.CollectionScreen

internal object InternalCollectionModuleAPI : CollectionModuleAPI {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        navGraphBuilder.composable(
            route = Screen.Collection.route
        ) {
            CollectionScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}