package com.lelestacia.hayate.common.shared.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun HayateCustomDropDownItem(
    text: String,
    onClick: () -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    DropdownMenuItem(
        text = {
            Text(text = text)
        },
        onClick = onClick,
        colors = MenuDefaults.itemColors(
            textColor =
            if (isDarkTheme) {
                Color.White
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )
    )
}