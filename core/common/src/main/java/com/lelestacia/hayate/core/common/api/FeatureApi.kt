package com.lelestacia.hayate.core.common.api

import androidx.navigation.NavGraphBuilder
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.state.HayateState

/**
 * Interface defining the API for a feature in the Hayate application. Features implement this interface
 * to register their navigation graph, manage state, and handle events within the application.
 *
 * @param navGraphBuilder The [NavGraphBuilder] used to build the navigation graph for the feature.
 * @param state The [HayateState] representing the state of the feature.
 * @param onEvent A lambda function to handle [HayateEvent]s triggered within the feature.
 *
 * @author LeleStacia
 * @since 23 January 2024
 */
interface FeatureApi {

    /**
     * Registers the navigation graph, manages the state, and handles events for the feature.
     *
     * @param navGraphBuilder The [NavGraphBuilder] used to build the navigation graph for the feature.
     * @param state The [HayateState] representing the state of the app.
     * @param onEvent A lambda function to handle [HayateEvent]s triggered within the feature.
     */
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        state: HayateState,
        onEvent: (HayateEvent) -> Unit,
    )
}