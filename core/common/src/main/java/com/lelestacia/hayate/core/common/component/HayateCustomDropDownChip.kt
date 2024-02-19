package com.lelestacia.hayate.core.common.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.lelestacia.hayate.core.theme.quickSandFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HayateCustomFilterChip(
    isActive: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    FilterChip(
        selected = isSelected,
        onClick = {
            onClick()
        },
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = quickSandFamily
                )
            )
        },
        trailingIcon = {
            AnimatedContent(
                targetState = isActive,
                label = "Chip Trailing Icon Animation"
            ) { isOpened ->
                when (isOpened) {
                    true -> Icon(
                        imageVector = Icons.Default.ArrowDropUp,

                        contentDescription = null
                    )

                    false -> Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.Transparent,
            labelColor = MaterialTheme.colorScheme.primary,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            selectedTrailingIconColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(15),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isActive,
            selectedBorderColor = Color.Transparent,
            borderColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = modifier.animateContentSize()
    )
}