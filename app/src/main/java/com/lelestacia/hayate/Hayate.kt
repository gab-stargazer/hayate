package com.lelestacia.hayate

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.api.FeatureApi
import com.lelestacia.hayate.common.shared.parcelable
import com.lelestacia.hayate.component.CustomAppBar
import com.lelestacia.hayate.feature.anime.collection.ui.CollectionScreen
import com.lelestacia.hayate.feature.anime.shared.model.Anime
import com.lelestacia.hayate.feature.anime.shared.parcelable.AnimeNavType
import com.lelestacia.hayate.feature.settings.ui.SettingScreen
import com.lelestacia.hayate.navigation.CustomBottomNavigation
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Hayate(
    navigationProvider: Set<@JvmSuppressWildcards FeatureApi>,
) {

    val navController: NavHostController = rememberNavController()
    val currentRoute by navController.currentBackStackEntryAsState()

    LaunchedEffect(key1 = Unit) {
        navController.currentBackStackEntryFlow.collectLatest {
            Timber.d("Current Route: ${it.destination.route}")
        }
    }

    Scaffold(
        topBar = {
            CustomAppBar(
                isDarkTheme = isSystemInDarkTheme()
            )
        },
        bottomBar = {
            CustomBottomNavigation(
                isDarkTheme = isSystemInDarkTheme(),
                selectedRoute = currentRoute?.destination?.route.orEmpty(),
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
        modifier = Modifier
            .systemBarsPadding()
            .navigationBarsPadding()
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screen.Exploration.route
        ) {

            navigationProvider.forEach { feature ->
                feature.registerGraph(
                    navGraphBuilder = this,
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            composable(Screen.Collection.route) {
                CollectionScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
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
                    },

//                    navArgument("data") {
//                        type = NavType.IntType
//                    }
                )
            ) {
                val device = it.arguments?.parcelable<Anime>("data")
//            val device = it.arguments?.getInt("data")
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