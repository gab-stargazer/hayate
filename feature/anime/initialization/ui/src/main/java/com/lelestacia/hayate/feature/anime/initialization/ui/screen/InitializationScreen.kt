package com.lelestacia.hayate.feature.anime.initialization.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.hayate.core.theme.AppTheme
import com.lelestacia.hayate.core.theme.spacing
import com.lelestacia.hayate.feature.anime.initialization.ui.R

@OptIn(ExperimentalComposeUiApi::class)
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
        modifier = Modifier
            .fillMaxSize()
            .semantics {
                testTagsAsResourceId = true
            }
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
            ),
            modifier = Modifier.testTag("text:loading-message")
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun PreviewInitializationScreen() {
    AppTheme(
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = false
    ) {
        Surface {
            InitializationScreen(
                isDarkTheme = isSystemInDarkTheme()
            )
        }
    }
}