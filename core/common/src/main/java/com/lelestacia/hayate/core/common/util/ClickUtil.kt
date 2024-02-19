package com.lelestacia.hayate.core.common.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle

@Composable
fun ClickUtil(
    onClicked: () -> Unit,
) {
    val currentLifecycle = LocalLifecycleOwner.current
    LaunchedEffect(key1 = Unit) {
        if (currentLifecycle.lifecycle.equals(Lifecycle.State.RESUMED)) {
            onClicked.invoke()
        }
    }
}

fun clickUtil(onClicked: () -> Unit, lifecycle: Lifecycle) {
    when (lifecycle.currentState == Lifecycle.State.RESUMED) {
        true -> onClicked()
        false -> Unit
    }
}