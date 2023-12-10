package com.lelestacia.hayate.feature.anime.detail.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AnimeScore(
    score: String,
    scoredBy: String,
    isDarkTheme: Boolean
) {
    val color = when (isDarkTheme) {
        true -> Color.White
        false -> Color.Black
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = score,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        )
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = color
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = scoredBy,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        )
        Icon(
            imageVector = Icons.Filled.People,
            contentDescription = null,
            tint = color
        )
    }
}