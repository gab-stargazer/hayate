package com.lelestacia.hayate.core.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lelestacia.hayate.core.theme.padding
import com.lelestacia.hayate.core.theme.quickSandFamily
import com.lelestacia.hayate.core.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HayateCustomIconChip(
    icon: ImageVector,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    title: String,
    onClick: () -> Unit,
) {
    val color = when (isDarkTheme) {
        true -> Color.White
        false -> Color.Black
    }
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing.small),
            modifier = Modifier.padding(
                horizontal = padding.medium,
                vertical = padding.small
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp),
                tint = color
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = quickSandFamily,
                    fontWeight = FontWeight.ExtraBold,
                    color = color
                )
            )
        }
    }
}