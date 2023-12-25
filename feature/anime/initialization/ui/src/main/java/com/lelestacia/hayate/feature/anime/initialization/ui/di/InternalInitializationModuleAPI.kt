package com.lelestacia.hayate.feature.anime.initialization.ui.di

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lelestacia.hayate.common.shared.DataState
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.api.FeatureApi
import com.lelestacia.hayate.feature.anime.initialization.ui.screen.InitializationScreen
import com.lelestacia.hayate.feature.anime.initialization.ui.viewmodel.InitializationViewModel
import kotlinx.coroutines.launch

internal object InternalInitializationModuleAPI : FeatureApi {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState
    ) {
        navGraphBuilder.composable(Screen.Init.route) {
            val scope = rememberCoroutineScope()
            val vm = hiltViewModel<InitializationViewModel>()
            val state by vm.state.collectAsStateWithLifecycle()

            val handleNavigation: () -> Unit = {
                navController.navigate(Screen.Exploration.route) {
                    popUpTo(Screen.Init.route) {
                        inclusive = true
                    }
                }
            }

            val isInitiated by vm.isInitiated.collectAsStateWithLifecycle()
            val isConfigFinished by vm.isConfigFinished.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                vm.checkForFirebaseConfig()
            }

            LaunchedEffect(key1 = state) {
                when (state.initializationResult) {
                    is DataState.Failed -> {
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = "Failed to initiate the app. It will try to initiate next time",
                                duration = SnackbarDuration.Long
                            )
                            handleNavigation()
                        }
                    }

                    is DataState.Success -> handleNavigation()

                    else -> Unit
                }
            }

            InitializationScreen()

            LaunchedEffect(key1 = isInitiated, isConfigFinished) {
                if (isConfigFinished) {
                    handleNavigation()
                }
            }
        }
    }
}