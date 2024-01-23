package com.lelestacia.hayate.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.lelestacia.hayate.core.common.event.HayateNavigationType
import com.lelestacia.hayate.core.common.state.UiState
import com.lelestacia.hayate.core.common.state.UiState.NotHandled

/**
 * Composable function to handle navigation based on the provided [navController] and [state].
 *
 * This function is designed to work with Jetpack Compose and is responsible for triggering
 * navigation actions when the state is of type [UiState.NotHandled]. It uses the [navController]
 * to perform navigation based on the type of [HayateNavigationType] present in the state data.
 * After performing the navigation action, the [postNavigate] lambda is invoked to execute any
 * additional actions.
 *
 * @param navController The [NavHostController] responsible for navigation within the application.
 * @param state The UI state that determines the type of navigation action to perform.
 * @param postNavigate Lambda function to be executed after performing the navigation.
 *
 * @see UiState
 * @see NotHandled
 * @see NavHostController
 * @see HayateNavigationType
 * @see LaunchedEffect
 * @since 23 January 2024
 */
@Composable
fun HandleNavigation(
    navController: NavHostController,
    state: UiState<HayateNavigationType>,
    postNavigate: () -> Unit,
) {
    LaunchedEffect(key1 = state) {
        if (state is NotHandled) {
            when (state.data) {
                is HayateNavigationType.Navigate -> navController.navigate(
                    route = (state.data as HayateNavigationType.Navigate).route,
                    navOptions = (state.data as HayateNavigationType.Navigate).options
                )

                HayateNavigationType.PopBackstack -> navController.popBackStack()
            }
            postNavigate()
        }
    }
}