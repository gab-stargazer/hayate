package com.lelestacia.hayate.common.shared.state

/**
 * Represents the different states of a user interface operation or event.
 * This sealed class is designed to encapsulate the outcome of a UI action, providing a clear
 * distinction between states that have been handled and those that haven't.
 *
 * The sealed class has two possible states:
 * - [NotHandled]: Represents a UI state where the associated data has not been handled yet.
 *   It holds the data related to the UI operation.
 * - [Handled]: Represents a UI state where the operation has been handled.
 *
 * @param T The type of data associated with the UI state when it has not been handled.
 *
 * @author Kamil Malik
 * @since 23 January 2024
 */
sealed class UiState<out T> {

    /**
     * Represents a UI state where the associated data has not been handled yet.
     * @property data The data associated with the UI state.
     */
    data class NotHandled<T>(val data: T) : UiState<T>()

    /**
     * Represents a UI state where the operation has been handled.
     * This is an object without any associated data.
     */
    data object Handled : UiState<Nothing>()
}

