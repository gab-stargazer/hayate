package com.lelestacia.hayate

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.systemuicontroller.SystemUiController
import com.lelestacia.hayate.component.CustomAppBar
import com.lelestacia.hayate.component.CustomBottomNavigation
import com.lelestacia.hayate.core.common.Screen
import com.lelestacia.hayate.core.common.api.FeatureApi
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.state.HayateState
import com.lelestacia.hayate.domain.state.AppBarState
import com.lelestacia.hayate.domain.state.BottomNavigationState

@Composable
fun Hayate(
    featureProvider: Set<@JvmSuppressWildcards FeatureApi>,
    navController: NavHostController,
    applicationState: HayateState,
    onApplicationEvent: (HayateEvent) -> Unit,
    appBarState: AppBarState,
    bottomNavigationState: BottomNavigationState,
    snackBarHostState: SnackbarHostState,
    uiController: SystemUiController,
) {
    Scaffold(
        topBar = {
            CustomAppBar(
                state = appBarState,
                onEvent = onApplicationEvent,
                uiController = uiController
            )
        },
        bottomBar = {
            CustomBottomNavigation(
                uiController = uiController,
                state = bottomNavigationState,
                onEvent = onApplicationEvent
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = { data ->
                    Snackbar(
                        shape = RoundedCornerShape(15.dp),
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
                        contentColor = when (isSystemInDarkTheme()) {
                            true -> Color.White
                            false -> MaterialTheme.colorScheme.onSurface
                        },
                        snackbarData = data
                    )
                }
            )
        },
        modifier = Modifier
            .systemBarsPadding()
            .navigationBarsPadding()
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screen.Init.route,
            modifier = Modifier
                .animateContentSize()
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            featureProvider.forEach { feature ->
                feature.registerGraph(
                    navGraphBuilder = this,
                    state = applicationState,
                    onEvent = onApplicationEvent
                )
            }
        }
    }
}