package com.lelestacia.hayate

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.api.FeatureApi
import com.lelestacia.hayate.common.shared.parcelable
import com.lelestacia.hayate.component.CustomAppBar
import com.lelestacia.hayate.component.CustomBottomNavigation
import com.lelestacia.hayate.domain.event.HayateEvent
import com.lelestacia.hayate.domain.viewmodel.HayateViewModel
import com.lelestacia.hayate.feature.anime.shared.model.Anime
import com.lelestacia.hayate.feature.anime.shared.parcelable.AnimeNavType
import com.lelestacia.hayate.feature.settings.ui.SettingScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            CustomAppBar(
                state = appBarState,
                navController = navController
            )
        },
        bottomBar = {
            CustomBottomNavigation(
                state = bottomNavigationState,
                onNavigationItemClicked = { route ->
                    navController.navigate(route) {
                        popUpTo(id = navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                })
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
            startDestination = Screen.Exploration.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .animateContentSize()
        ) {

            navigationProvider.forEach { feature ->
                feature.registerGraph(
                    navGraphBuilder = this,
                    navController = navController,
                    snackBarHostState = snackBarHostState
                )
            }

            composable(Screen.More.route) {
                SettingScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            composable(
                Screen.Detail.route,
                arguments = listOf(
                    navArgument("data") {
                        type = AnimeNavType()
                    }
                )
            ) {
                val device = it.arguments?.parcelable<Anime>("data")

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Detail")
                        })
                    }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = device.toString())
                    }
                }
            }
        }
    }
}