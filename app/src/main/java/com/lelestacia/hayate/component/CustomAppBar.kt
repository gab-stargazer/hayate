package com.lelestacia.hayate.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.lelestacia.hayate.domain.state.AppBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    state: AppBarState,
    navController: NavHostController,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = state.appBarTitle),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = when (state.isDarkTheme) {
                        true -> MaterialTheme.colorScheme.primary
                        false -> MaterialTheme.colorScheme.primary
                    }
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            AnimatedVisibility(visible = state.shouldNavigationIconBeVisible) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}