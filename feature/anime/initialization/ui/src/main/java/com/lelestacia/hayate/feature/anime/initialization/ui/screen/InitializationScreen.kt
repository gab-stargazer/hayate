package com.lelestacia.hayate.feature.anime.initialization.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.hayate.core.theme.AppTheme
import com.lelestacia.hayate.core.theme.spacing
import com.lelestacia.hayate.feature.anime.initialization.ui.R

@Composable
internal fun InitializationScreen(
    isDarkTheme: Boolean,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = spacing.small,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
        Text(
            text = stringResource(R.string.loading_message),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = when (isDarkTheme) {
                    true -> Color.White
                    false -> Color.Black
                }
            )
        )
    }
}

@Preview
@Composable
internal fun PreviewInitializationScreen() {
    AppTheme(
        dynamicColor = false
    ) {
        InitializationScreen(
            isDarkTheme = false
        )
    }
}