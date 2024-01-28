package com.lelestacia.hayate.core.common.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class MessageString(val message: String) : UiText()
    data class ResourceID(@StringRes val id: Int, val args: List<Any>) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is MessageString -> message
            is ResourceID -> context.getString(id, *args.toTypedArray())
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is MessageString -> message
            is ResourceID -> stringResource(id = id, *args.toTypedArray())
        }
    }
}