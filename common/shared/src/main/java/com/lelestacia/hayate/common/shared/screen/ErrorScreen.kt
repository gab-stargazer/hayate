package com.lelestacia.hayate.common.shared.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.lelestacia.hayate.common.theme.quickSandFamily
import com.lelestacia.hayate.common.theme.spacing

@Composable
fun ErrorScreen(
    errorMessage: String,
    onRetry: () -> Unit,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.small, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textColor = when (isDarkTheme) {
            true -> Color.White
            false -> Color.Black
        }
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleSmall.copy(
                fontFamily = quickSandFamily,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        )
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                contentColor = textColor
            )
        ) {
            //  TODO: Change into resource later
            Text(text = "Retry")
        }
    }
}