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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.api.FeatureApi
import com.lelestacia.hayate.common.shared.event.HayateEvent
import com.lelestacia.hayate.common.shared.util.CollectInLaunchEffect
import com.lelestacia.hayate.common.shared.util.HandleEvent
import com.lelestacia.hayate.component.CustomAppBar
import com.lelestacia.hayate.component.CustomBottomNavigation
import com.lelestacia.hayate.domain.viewmodel.HayateViewModel
import com.lelestacia.hayate.feature.settings.ui.SettingScreen
import com.lelestacia.hayate.util.HandleNavigation
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun Hayate(
    featureProvider: Set<@JvmSuppressWildcards FeatureApi>,
    vm: HayateViewModel = hiltViewModel(),
) {

    val appBarState by vm.appBarState.collectAsStateWithLifecycle()
    val bottomNavigationState by vm.bottomNavigationState.collectAsStateWithLifecycle()

    val navController: NavHostController = rememberNavController()
    val snackBarHostState by remember {
        mutableStateOf(SnackbarHostState())
    }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val uiController = rememberSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()
    val surfaceColor = MaterialTheme.colorScheme.surface

    val backStackEntry by navController
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

    LaunchedEffect(key1 = Unit) {
        vm.onEvent(HayateEvent.OnDarkThemeChanged(isDarkTheme))
    }

    /**
     * Observes the [HayateViewModel.navigationRoute] StateFlow from the [HayateViewModel] and collects it as a State using the
     * [collectAsStateWithLifecycle] extension function. It then uses the collected route to update the
     * navigation within the application by invoking the [HandleNavigation] function.
     *
     * @param vm The [HayateViewModel] responsible for managing navigation states.
     * @param navController The NavController used for navigation within the application.
     *
     * @see HayateViewModel.navigationRoute
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
    val navigationRoute by vm.navigationRoute.collectAsStateWithLifecycle()
    HandleNavigation(
        navController = navController,
        navigation = navigationRoute,
        postNavigate = vm::handleNavigation
    )

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

    val snackBarState by vm.snackBarMessage.collectAsStateWithLifecycle()
    HandleEvent(
        event = snackBarState,
        onEvent = { state ->
            state?.let { uiText ->
                scope.launch {
                    snackBarHostState.showSnackbar(uiText.asString(context = context))
                }
            }
        },
        postEvent = vm::onSnackBarMessageHandled
    )

    /*
     *  New navigation system works by having a channel that host a custom data class
     *  representing a navigation with route as string and nullable options.
     *  Basically after navigation happened, channel should be cleared since it was consumed
     *  as flow (FAILED).
     *
     *  Created on 16th January 2024
     *
     *  Issues list:
     *      1. App will crash when theme changes due to consume as flow triggered once again
     *          condition: Using Channel then consumeAsFlow to convert the channel into flow
     *             The reason why it crashed is because when the app reconstructed because of
     *             system UI changed, the flow got recollected even tho it supposed to be
     *             collected only once
     *  NOTE:
     *      If you have a fix for this or know something about this, please let me know.
     *      handling the navigation as a stateflow means i need to handle the state after
     *      the event is fired, that's why i'm thinking of staying on Channel when found a
     *      fix for that
     */

//
//    val navigationState by vm.navigationState.collectAsStateWithLifecycle()
//    HandleEvent(
//        event = navigationState,
//        onEvent = { navigation ->
//            navigation?.let {
//                when (navigation) {
//                    is HayateNavigationType.Navigate -> navController.navigate(
//                        route = navigation.route,
//                        navOptions = navigation.options
//                    )
//
//                    HayateNavigationType.PopBackstack -> navController.popBackStack()
//                }
//            }
//        },
//        postEvent = vm::onNavigationStateHandled
//    )


    navController.currentBackStackEntryFlow.CollectInLaunchEffect(
        key = Unit,
        block = { navBackStackEntry: NavBackStackEntry ->
            navBackStackEntry.destination.route?.let { currentRoute: String ->
                vm.onEvent(HayateEvent.OnDestinationChanged(currentRoute))
            }
        }
    )
}