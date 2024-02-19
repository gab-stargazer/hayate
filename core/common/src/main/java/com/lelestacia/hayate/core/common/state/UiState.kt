package com.lelestacia.hayate.core.common.state

import com.lelestacia.hayate.core.common.util.UiText

/**
 * Represents different states for data loading and retrieval within the Hayate application.
 * This sealed interface encapsulates the possible outcomes of data operations, including loading,
 * success with data, failure with an error message, or no data state.
 *
 * @param T The type of data associated with the state when it represents success.
 *
 * @author Kamil Malik
 * @since 23 January 2024
 */
sealed interface UiState<out T> {

    /**
     * Represents a data state indicating that data is currently being loaded.
     */
    data object Loading : UiState<Nothing>

    /**
     * Represents a data state indicating a successful operation with associated data.
     * @property data The data associated with the success state.
     */
    data class Success<T>(val data: T) : UiState<T>

    /**
     * Represents a data state indicating a failure with a specific error message.
     * @property error The error message associated with the failure state.
     */
    data class Failed(val error: UiText) : UiState<Nothing>

    /**
     * Represents a data state indicating that there is no data to display or retrieve.
     */
    data object None : UiState<Nothing>
}