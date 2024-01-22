package com.lelestacia.hayate.feature.anime.initialization.ui.di

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.api.FeatureApi
import com.lelestacia.hayate.common.shared.event.HayateEvent
import com.lelestacia.hayate.common.shared.event.HayateNavigationType
import com.lelestacia.hayate.feature.anime.initialization.ui.screen.InitializationScreen
import kotlinx.coroutines.delay

internal object InternalInitializationModuleAPI : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        navGraphBuilder.composable(Screen.Init.route) {

//            TODO: Clean this mess later, this things more messy than my future
//            val isConfigFinished by vm.isConfigFinished.collectAsStateWithLifecycle()
//            LaunchedEffect(key1 = Unit) {
//                vm.checkForFirebaseConfig()
//            }

//            Temporarily disabled due to unsure about how the feature should be designed
//
//            val state by vm.state.collectAsStateWithLifecycle()
//            val scope = rememberCoroutineScope()
//
//            LaunchedEffect(key1 = state) {
//                when (state.initializationResult) {
//                    is DataState.Failed -> {
//                        scope.launch {
//                            snackBarHostState.showSnackbar(
//                                message = "Failed to initiate the app. It will try to initiate next time",
//                                duration = SnackbarDuration.Long
//                            )
//                            handleNavigation()
//                        }
//                    }
//
//                    is DataState.Success -> handleNavigation()
//
//                    else -> Unit
//                }
//            }

            InitializationScreen()

//            LaunchedEffect(key1 = isConfigFinished) {
//                if (isConfigFinished) {
//                    handleNavigation()
//                }
//            }

            LaunchedEffect(key1 = Unit) {
                delay(1500)
                val navigationEvent = HayateEvent.OnNavigate(
                    HayateNavigationType.Navigate(
                        route = Screen.Exploration.route,
                        options = navOptions {
                            popUpTo(Screen.Init.route) {
                                inclusive = true
                            }
                        }
                    )
                )

                onEvent(navigationEvent)
            }
        }
    }
}