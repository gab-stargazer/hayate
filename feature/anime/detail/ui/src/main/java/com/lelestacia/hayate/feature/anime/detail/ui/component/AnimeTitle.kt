package com.lelestacia.hayate.feature.anime.detail.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.lelestacia.hayate.core.theme.quickSandFamily

@Composable
internal fun AnimeTitle(
    title: String,
    titleJapanese: String?,
    isDarkTheme: Boolean
) {
    val textColor = when (isDarkTheme) {
        true -> Color.White
        false -> Color.Black
    }
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            fontFamily = quickSandFamily,
            color = textColor
        )
    )
    titleJapanese?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = textColor
            )
        )
    }
}