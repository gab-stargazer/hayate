package com.lelestacia.hayate.util

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.lelestacia.hayate.core.common.state.UiState
import com.lelestacia.hayate.core.common.state.UiState.NotHandled
import com.lelestacia.hayate.core.common.util.UiText

/**
 * Composable function to handle and display a Snackbar based on the provided [snackBarHost] and [state].
 *
 * This function is designed to work with Jetpack Compose and is responsible for showing a Snackbar
 * when the state is of type [UiState.NotHandled]. It extracts the message from the state data
 * and displays the Snackbar using the [snackBarHost]. After displaying the Snackbar, the [postSnackBar]
 * lambda is invoked to perform any additional actions.
 *
 * @param snackBarHost The [SnackbarHostState] responsible for showing the Snackbar.
 * @param state The UI state that determines whether to show the Snackbar or not.
 * @param postSnackBar Lambda function to be executed after showing the Snackbar.
 *
 * @see UiState
 * @see NotHandled
 * @see SnackbarHostState
 * @see Context
 * @see LocalContext
 * @see LaunchedEffect
 * @author Kamil Malik
 * @since 23 January 2024
 */
@Composable
fun HandleSnackBar(
    snackBarHost: SnackbarHostState,
    state: UiState<UiText>,
    postSnackBar: () -> Unit,
) {
    val context: Context = LocalContext.current
    LaunchedEffect(state) {
        if (state is NotHandled) {
            snackBarHost.showSnackbar(
                message = state.data.asString(context)
            )
            postSnackBar()
        }
    }
}