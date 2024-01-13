package com.lelestacia.hayate

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.api.FeatureApi
import com.lelestacia.hayate.common.shared.event.HayateEvent
import com.lelestacia.hayate.component.CustomAppBar
import com.lelestacia.hayate.component.CustomBottomNavigation
import com.lelestacia.hayate.domain.viewmodel.HayateViewModel
import com.lelestacia.hayate.feature.settings.ui.SettingScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Hayate(
    navigationProvider: Set<@JvmSuppressWildcards FeatureApi>,
    vm: HayateViewModel = hiltViewModel()
) {

    val appBarState by vm.appBarState.collectAsStateWithLifecycle()
    val bottomNavigationState by vm.bottomNavigationState.collectAsStateWithLifecycle()

    val navController: NavHostController = rememberNavController()
    val snackBarHostState by remember {
        mutableStateOf(SnackbarHostState())
    }

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        navController.currentBackStackEntryFlow.onEach { navBackStackEntry ->
            navBackStackEntry.destination.route?.let { validRoute ->
                vm.onEvent(HayateEvent.OnDestinationChanged(validRoute))
            }
        }.launchIn(scope)
    }

    val uiController = rememberSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()
    val surfaceColor = MaterialTheme.colorScheme.surface

    val backStackEntry by navController.currentBackStackEntryFlow
        .collectAsStateWithLifecycle(initialValue = null)

    DisposableEffect(key1 = backStackEntry) {
        uiController.setStatusBarColor(
            color = surfaceColor,
            darkIcons = !isDarkTheme
        )
        onDispose { }
    }

    LaunchedEffect(key1 = Unit) {
        vm.onEvent(HayateEvent.OnDarkThemeChanged(isDarkTheme))
    }


    Scaffold(
        topBar = {
            if (backStackEntry?.destination?.route != Screen.Init.route) {
                CustomAppBar(
                    state = appBarState,
                    onEvent = vm::onEvent,
                    navController = navController
                )
            }
        },
        bottomBar = {
            if (backStackEntry?.destination?.route != Screen.Init.route) {
                CustomBottomNavigation(
                    navController = navController,
                    uiController = uiController,
                    state = bottomNavigationState
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
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
                .fillMaxSize()
                .padding(paddingValues)
                .animateContentSize()
        ) {

            navigationProvider.forEach { feature ->
                feature.registerGraph(
                    navGraphBuilder = this,
                    navController = navController,
                    snackBarHostState = snackBarHostState,
                    onEvent = vm::onEvent
                )
            }

            composable(Screen.More.route) {
                SettingScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    }


}