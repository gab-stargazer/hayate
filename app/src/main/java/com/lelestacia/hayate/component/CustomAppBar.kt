package com.lelestacia.hayate.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.lelestacia.hayate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    isDarkTheme: Boolean
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.japanese_app_name),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = when (isDarkTheme) {
                        true -> MaterialTheme.colorScheme.primary
                        false -> MaterialTheme.colorScheme.primary
                    }
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}