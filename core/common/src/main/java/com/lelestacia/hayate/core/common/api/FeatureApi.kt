package com.lelestacia.hayate.core.common.api

import androidx.navigation.NavGraphBuilder
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.state.HayateState

/**
 * Interface FeatureApi defines methods to register a new feature as a screen in this app.
 */
interface FeatureApi {
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        state: HayateState,
        onEvent: (HayateEvent) -> Unit,
    )
}