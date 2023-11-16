package com.lelestacia.hayate.common.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.lelestacia.hayate.common.theme.quickSandFamily
import com.lelestacia.hayate.common.theme.spacing

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.small, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.loading),
            style = MaterialTheme.typography.titleSmall.copy(
                fontFamily = quickSandFamily,
                fontWeight = FontWeight.Bold
            )
        )
        LinearProgressIndicator()
    }
}

@Preview
@Composable
fun PreviewLoadingScreen() {
    AppTheme {
        LoadingScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}