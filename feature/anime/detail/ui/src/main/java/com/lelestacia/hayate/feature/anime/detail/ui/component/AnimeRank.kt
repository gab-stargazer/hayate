package com.lelestacia.hayate.feature.anime.detail.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.lelestacia.hayate.feature.anime.detail.ui.R

@Composable
fun AnimeRank(
    rank: String,
    isDarkTheme: Boolean
) {
    val textColor = when (isDarkTheme) {
        true -> Color.White
        false -> Color.Black
    }
    Text(
        text = stringResource(id = R.string.rank, rank),
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Black,
            color = textColor
        )
    )
}