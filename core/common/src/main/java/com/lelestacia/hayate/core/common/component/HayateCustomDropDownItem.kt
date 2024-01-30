package com.lelestacia.hayate.core.common.component

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HayateCustomDropDownItem(
    text: String,
    onClick: () -> Unit,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
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
        ),
        modifier = modifier
    )
}