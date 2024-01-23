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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lelestacia.hayate.component.CustomAppBar
import com.lelestacia.hayate.component.CustomBottomNavigation
import com.lelestacia.hayate.core.common.Screen
import com.lelestacia.hayate.core.common.api.FeatureApi
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.state.HayateState
import com.lelestacia.hayate.domain.state.AppBarState
import com.lelestacia.hayate.domain.state.BottomNavigationState
import com.lelestacia.hayate.domain.viewmodel.HayateViewModel
import com.lelestacia.hayate.feature.settings.ui.SettingScreen
import com.lelestacia.hayate.util.HandleNavigation
import com.lelestacia.hayate.util.HandleSnackBar
import timber.log.Timber

@Composable
fun Hayate(
    featureProvider: Set<@JvmSuppressWildcards FeatureApi>,
    vm: HayateViewModel = hiltViewModel(),
) {

    val appBarState: AppBarState by vm.appBarState.collectAsStateWithLifecycle()
    val bottomNavigationState: BottomNavigationState by vm.bottomNavigationState.collectAsStateWithLifecycle()

    val navController: NavHostController = rememberNavController()
    val snackBarHostState: SnackbarHostState by remember {
        mutableStateOf(SnackbarHostState())
    }

    val uiController: SystemUiController = rememberSystemUiController()
    val isDarkTheme: Boolean = isSystemInDarkTheme()
    val surfaceColor: Color = MaterialTheme.colorScheme.surface

    val backStackEntry: NavBackStackEntry? by navController
        .currentBackStackEntryFlow
        .collectAsStateWithLifecycle(initialValue = null)

    DisposableEffect(key1 = backStackEntry) {
        uiController.setStatusBarColor(
            color = surfaceColor,
            darkIcons = !isDarkTheme
        )

        onDispose {
            Timber.i("Disposable effect is disposed. Hmmm, weird sentence")
        }
    }

    DisposableEffect(Unit) {
        vm.onEvent(HayateEvent.DarkThemeChanged(isDarkTheme))

        onDispose {
            Timber.w("Hayate leaves composition tree")
        }
    }

    /**
     * Observes the [HayateViewModel.navigationState] StateFlow from the [HayateViewModel] and collects it as a State using the
     * [collectAsStateWithLifecycle] extension function. It then uses the collected route to update the
     * navigation within the application by invoking the [HandleNavigation] function.
     *
     * @param vm The [HayateViewModel] responsible for managing navigation states.
     * @param navController The NavController used for navigation within the application.
     *
     * @see HayateViewModel.navigationState
     * @see collectAsStateWithLifecycle
     * @see HandleNavigation
     *
     * Example Usage:
     * ```
     * val navigationRoute by vm.navigationRoute.collectAsStateWithLifecycle()
     * HandleNavigation(
     *     navController = navController,
     *     navigation = navigationRoute,
     *     postNavigate = vm::handleNavigation
     * )
     * ```
     */
    val navigationState by vm.navigationState.collectAsStateWithLifecycle()
    HandleNavigation(
        navController = navController,
        state = navigationState,
        postNavigate = vm::handleNavigation
    )

    /**
     * Collects the snackBarState from the [vm] (ViewModel) using [collectAsStateWithLifecycle],
     * and then uses [HandleSnackBar] composable to display a Snackbar based on the collected state.
     *
     * This code snippet is part of a Compose UI setup, where the snackBarState is observed and
     * converted into a state that Compose can recompose on. The [HandleSnackBar] composable is then
     * invoked to handle the Snackbar display based on the current state. The [SnackbarHostState] is
     * used as the [SnackbarHostState] responsible for showing the Snackbar, and postSnackBar is a
     * lambda function to be executed after showing the Snackbar.
     *
     * Example usage:
     * ```kotlin
     * val snackBarState by vm.snackBarState.collectAsStateWithLifecycle()
     * HandleSnackBar(
     *     snackBarHost = snackBarHostState,
     *     state = snackBarState,
     *     postSnackBar = vm::handleSnackBar
     * )
     * ```
     *
     * @param snackBarHost The [SnackbarHostState] responsible for showing the Snackbar.
     * @param state The UI state that determines whether to show the Snackbar or not.
     * @param postSnackBar Lambda function to be executed after showing the Snackbar.
     * @see vm
     * @see snackBarState
     * @see HandleSnackBar
     * @see collectAsStateWithLifecycle
     */
    val snackBarState by vm.snackBarState.collectAsStateWithLifecycle()
    HandleSnackBar(
        snackBarHost = snackBarHostState,
        state = snackBarState,
        postSnackBar = vm::handleSnackBar
    )

    val applicationState: HayateState by vm.applicationState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CustomAppBar(
                state = appBarState,
                onEvent = vm::onEvent
            )
        },
        bottomBar = {
            CustomBottomNavigation(
                uiController = uiController,
                state = bottomNavigationState,
                onEvent = vm::onEvent
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = {
                    Snackbar(
                        shape = RoundedCornerShape(15.dp),
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
                        contentColor = when (isSystemInDarkTheme()) {
                            true -> Color.White
                            false -> MaterialTheme.colorScheme.onSurface
                        },
                        snackbarData = it
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
                .fillMaxSize()
                .padding(paddingValues)
                .animateContentSize()
        ) {

            /*
             *  This part just need a repetition for each feature provider
             */

            featureProvider.forEach { feature ->
                feature.registerGraph(
                    navGraphBuilder = this,
                    state = applicationState,
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

    LaunchedEffect(key1 = backStackEntry) {
        backStackEntry?.destination?.route?.let { currentRoute ->
            vm.onEvent(HayateEvent.DestinationChanged(currentRoute))
        }
    }
}