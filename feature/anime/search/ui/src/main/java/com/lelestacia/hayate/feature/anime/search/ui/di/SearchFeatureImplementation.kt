package com.lelestacia.hayate.feature.anime.search.ui.di

import android.net.Uri
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.hayate.core.common.Screen
import com.lelestacia.hayate.core.common.api.FeatureApi
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.event.HayateNavigationType
import com.lelestacia.hayate.core.common.state.HayateState
import com.lelestacia.hayate.core.common.util.toJson
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.search.ui.component.SearchScreen
import com.lelestacia.hayate.feature.anime.search.ui.presenter.SearchState
import com.lelestacia.hayate.feature.anime.search.ui.viewmodel.SearchViewModel
import javax.inject.Inject

internal class SearchFeatureImplementation @Inject constructor() : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        state: HayateState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        navGraphBuilder.composable(
            route = Screen.Search.route,
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
        ) {
            val vm: SearchViewModel = hiltViewModel()
            LaunchedEffect(key1 = state.searchQuery) {
                vm.updateSearchQuery(state.searchQuery)
            }

            val pagingAnime: LazyPagingItems<Anime> = vm.searchAnime.collectAsLazyPagingItems()
            val searchState: SearchState by vm.screenState.collectAsStateWithLifecycle()

            SearchScreen(
                query = state.searchQuery,
                state = searchState,
                isDarkTheme = state.isDarkTheme,
                onEvent = vm::onEvent,
                anime = pagingAnime,
                onClicked = { clickedAnime ->
                    val jsonAnime: String = toJson(clickedAnime)
                    val event = HayateEvent.Navigate(
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