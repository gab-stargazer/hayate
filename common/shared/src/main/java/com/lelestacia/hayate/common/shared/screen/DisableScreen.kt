package com.lelestacia.hayate.common.shared.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.lelestacia.hayate.common.theme.quickSandFamily

@Composable
fun DisableScreen(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = "Feature is temporarily disabled or not available",
            style = MaterialTheme.typography.titleSmall.copy(
                fontFamily = quickSandFamily,
                fontWeight = FontWeight.Bold,
                color = when (isDarkTheme) {
                    true -> Color.White
                    false -> Color.Black
                }
            )
        )
    }
}