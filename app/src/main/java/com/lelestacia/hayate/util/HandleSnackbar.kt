package com.lelestacia.hayate.util

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.lelestacia.hayate.core.common.util.UiText
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach

/**
 *  Composable function to handle and display a Snackbar based on the provided [snackBarHost] and [snackBarChannel].
 *  This function is designed to work with Jetpack Compose and is responsible for showing a Snackbar
 *  when a message is received from the [snackBarChannel]. It extracts the message from the received data
 *  and displays the Snackbar using the [snackBarHost].
 *
 *  @param snackBarHost The [SnackbarHostState] responsible for showing the Snackbar.
 *  @param snackBarChannel The channel through which messages for Snackbar display are received.
 *  @see SnackbarHostState
 *  @see ReceiveChannel
 *  @see Context
 *  @see LocalContext
 *  @see LaunchedEffect
 *  @author Kamil Malik
 *  @since 23 January 2024
 */
@Composable
fun HandleSnackBar(
    snackBarHost: SnackbarHostState,
    snackBarChannel: ReceiveChannel<UiText>,
) {
    val context: Context = LocalContext.current
    LaunchedEffect(Unit) {
        snackBarChannel.consumeEach { data ->
            snackBarHost.showSnackbar(
                message = data.asString(context)
            )
        }
    }
}