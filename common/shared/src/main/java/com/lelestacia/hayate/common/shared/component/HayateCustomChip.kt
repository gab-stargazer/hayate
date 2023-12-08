package com.lelestacia.hayate.common.shared.component

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.lelestacia.hayate.common.theme.quickSandFamily

@Composable
fun HayateCustomChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {

    //  TODO: Fix Coloring

    AssistChip(
        onClick = onClick,
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = quickSandFamily,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor =
            when (isDarkTheme) {
                true -> MaterialTheme.colorScheme.tertiaryContainer
                false -> MaterialTheme.colorScheme.tertiary
            },
            labelColor =
            when (isDarkTheme) {
                true -> Color.White
                false -> Color.White
            }
        ),
        border = AssistChipDefaults.assistChipBorder(
            borderWidth = 0.dp,
            borderColor = Color.Transparent
        ),
        modifier = modifier
    )
}

@Preview(
    name = "Preview Chip in Day Mode"
)
@Preview(
    name = "Preview Chip in Night Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewHayateCustomChip() {
    AppTheme(
        dynamicColor = false
    ) {
        Surface {
            HayateCustomChip(
                text = "Adventure",
                onClick = {}
            )
        }
    }
}