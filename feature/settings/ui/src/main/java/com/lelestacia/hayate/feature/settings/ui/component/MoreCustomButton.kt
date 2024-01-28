package com.lelestacia.hayate.feature.settings.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lelestacia.hayate.core.theme.padding
import com.lelestacia.hayate.core.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MoreCustomButton(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        shape = RectangleShape,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding.large)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = when (isDarkTheme) {
                    true -> Color.White
                    false -> Color.Black
                },
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = label,
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
}