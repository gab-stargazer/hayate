package com.lelestacia.hayate.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.lelestacia.hayate.core.common.event.HayateNavigationType
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach

/**
 * Composable function to handle navigation based on the provided [navController] and [navigationChannel].
 *
 * This function is designed to work with Jetpack Compose and is responsible for triggering
 * navigation actions when a message is received from the [navigationChannel]. It uses the [navController]
 * to perform navigation based on the type of [HayateNavigationType] present in the received data.
 * additional actions.
 *
 * @param navController The [NavHostController] responsible for navigation within the application.
 * @param navigationChannel The channel through which messages for navigation are received.
 *
 * @see NavHostController
 * @see HayateNavigationType
 * @see LaunchedEffect
 * @since 23 January 2024
 */
@Composable
fun HandleNavigation(
    navController: NavHostController,
    navigationChannel: ReceiveChannel<HayateNavigationType>,
) {
    LaunchedEffect(key1 = Unit) {
        navigationChannel.consumeEach { navType ->
            when (navType) {
                is HayateNavigationType.Navigate -> navController.navigate(
                    route = navType.route.value,
                    navOptions = navType.options
                )

                is HayateNavigationType.NavigateWithTitle -> navController.navigate(
                    route = navType.route.value,
                    navOptions = navType.options
                )

                else -> navController.popBackStack()
            }
        }
    }
}