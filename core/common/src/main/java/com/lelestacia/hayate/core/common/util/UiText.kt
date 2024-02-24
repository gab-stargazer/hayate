package com.lelestacia.hayate.core.common.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lelestacia.hayate.core.common.util.UiText.MessageString
import com.lelestacia.hayate.core.common.util.UiText.ResourceID

/**
 * Represents a sealed class for handling different types of UI text in the Hayate application.
 * This class provides a unified approach for dealing with both raw strings and string resources with arguments.
 *
 * @property MessageString Represents a simple message with a [String] content.
 * @property ResourceID Represents a string resource with a resource ID and optional argument list.
 *
 * @method asString(context: Context): String Converts the UiText to a [String] based on the provided context.
 * @method asString(): String Converts the UiText to a [String] in a Composable function.
 *
 * @author LeleStacia
 * @since 1 February 2024
 */
sealed class UiText {
    /**
     * Represents a simple message with a [String] content.
     *
     * @property message The content of the message.
     */
    data class MessageString(val message: String) : UiText()

    /**
     * Represents a string resource with a resource ID and optional argument list.
     *
     * @property id The resource ID of the string resource.
     * @property args The list of arguments to be formatted into the string resource.
     */
    data class ResourceID(@StringRes val id: Int, val args: List<Any>) : UiText()

    /**
     * Converts the UiText to a [String] based on the provided context.
     *
     * @param context The Android application context.
     * @return The converted [String] representation of UiText.
     */
    fun asString(context: Context): String {
        return when (this) {
            is MessageString -> message
            is ResourceID -> context.getString(id, *args.toTypedArray())
        }
    }

    /**
     * Converts the UiText to a [String] in a Composable function.
     *
     * @return The converted [String] representation of UiText for Jetpack Compose.
     */
    @Composable
    fun asString(): String {
        return when (this) {
            is MessageString -> message
            is ResourceID -> stringResource(id = id, *args.toTypedArray())
        }
    }
}