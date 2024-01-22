package com.lelestacia.hayate.feature.anime.search.ui.di

import android.net.Uri
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.api.FeatureApi
import com.lelestacia.hayate.common.shared.event.HayateEvent
import com.lelestacia.hayate.common.shared.event.HayateNavigationType
import com.lelestacia.hayate.common.shared.util.toJson
import com.lelestacia.hayate.feature.anime.search.ui.component.SearchScreen
import com.lelestacia.hayate.feature.anime.search.ui.viewmodel.SearchViewModel
import javax.inject.Inject

internal class FeatureImplementation @Inject constructor() : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        navGraphBuilder.composable(
            route = Screen.Search.route,
            arguments = listOf(
                navArgument("query") {
                    type = NavType.StringType
                }
            ),
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.Exploration.route -> slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) + fadeIn()
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    Screen.Exploration.route -> slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) + fadeOut()
                    else -> null
                }
            }
        ) { navBackStackEntry ->
            val query = navBackStackEntry.arguments?.getString("query").orEmpty()
            val vm: SearchViewModel = hiltViewModel()
            LaunchedEffect(key1 = Unit) {
                vm.updateSearchQuery(query)
            }

            val pagingAnime = vm.searchAnime.collectAsLazyPagingItems()
            val state by vm.screenState.collectAsStateWithLifecycle()

            SearchScreen(
                state = state,
                onEvent = vm::onEvent,
                anime = pagingAnime,
                onClicked = { clickedAnime ->
                    val jsonAnime = toJson(clickedAnime)
                    val event = HayateEvent.OnNavigate(
                        HayateNavigationType.Navigate(
                            route = Screen.Detail.createRoute(
                                jsonAnime = Uri.encode(jsonAnime)
                            ),
                            options = navOptions {
                                launchSingleTop = true
                            }
                        )
                    )
                    onEvent(event)
                    vm.insertAnime(clickedAnime)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}