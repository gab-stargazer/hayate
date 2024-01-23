package com.lelestacia.hayate.feature.anime.detail.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lelestacia.hayate.core.theme.padding
import com.lelestacia.hayate.core.theme.spacing

@Composable
internal fun CardSection(
    content: @Composable ColumnScope.() -> Unit,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor =
            if (isDarkTheme) {
                MaterialTheme.colorScheme.surfaceVariant
            } else {
                MaterialTheme.colorScheme.primaryContainer
            },
            contentColor =
            if (isDarkTheme) {
                Color.White
            } else {
                MaterialTheme.colorScheme.onPrimaryContainer
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = padding.small)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = spacing.small),
            modifier = Modifier.padding(all = padding.large)
        ) {
            content()
        }
    }
}