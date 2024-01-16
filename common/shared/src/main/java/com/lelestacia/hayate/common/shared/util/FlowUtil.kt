package com.lelestacia.hayate.common.shared.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
inline fun <T> HandleEvent(
    event: T,
    crossinline onEvent: (T) -> Unit,
    crossinline postEvent: () -> Unit,
) {
    LaunchedEffect(key1 = event) {
        onEvent(event)
        postEvent()
    }
}

@Composable
inline fun <reified T> Flow<T>.CollectInLaunchEffect(
    key: Any,
    noinline block: suspend (T) -> Unit,
) {
    val lifeCycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(key1 = key) {
        lifeCycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@CollectInLaunchEffect.collectLatest(block)
        }
    }
}

@Composable
inline fun <reified T> Flow<T>.CollectInLaunchEffect(
    noinline block: suspend (T) -> Unit,
) {
    val lifeCycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(key1 = Unit) {
        lifeCycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@CollectInLaunchEffect.collectLatest(block)
        }
    }
}