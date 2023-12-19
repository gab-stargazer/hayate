package com.lelestacia.hayate.common.shared

import com.lelestacia.hayate.common.shared.util.UiText

sealed interface DataState<out T> {
    data object Loading : DataState<Nothing>
    data class Success<T>(val data: T) : DataState<T>
    data class Failed(val error: UiText) : DataState<Nothing>
    data object None : DataState<Nothing>
}