package com.lelestacia.hayate.feature.anime.initialization.ui.di

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.lelestacia.hayate.core.common.Screen
import com.lelestacia.hayate.core.common.api.FeatureApi
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.event.HayateNavigationType
import com.lelestacia.hayate.core.common.state.HayateState
import com.lelestacia.hayate.core.common.util.Route
import com.lelestacia.hayate.core.common.util.Title
import com.lelestacia.hayate.feature.anime.initialization.ui.screen.InitializationScreen
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

internal class InitializationFeatureImplementation @Inject constructor() : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        state: HayateState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        navGraphBuilder.composable(Screen.Init.route) {
            InitializationScreen(
                isDarkTheme = state.isDarkTheme
            )

            LaunchedEffect(key1 = Unit) {
                delay(1500)

                Timber.d("NAVIGATE")
                val navigationEvent = HayateEvent.Navigate(
                    HayateNavigationType.Navigate(
                        route = Route(Screen.Exploration.route),
                        options = navOptions {
                            popUpTo(Screen.Init.route) {
                                inclusive = true
                            }
                        },
                        navTitle = Title(Screen.Init::class.simpleName.orEmpty())
                    )
                )

                onEvent(navigationEvent)
            }
        }
    }
}