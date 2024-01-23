package com.lelestacia.hayate.feature.anime.detail.ui.di

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.navigation.navArgument
import com.lelestacia.hayate.core.common.Screen
import com.lelestacia.hayate.core.common.api.FeatureApi
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.event.HayateNavigationType
import com.lelestacia.hayate.core.common.state.HayateState
import com.lelestacia.hayate.core.common.util.parcelable
import com.lelestacia.hayate.feature.anime.core.common.parcelable.AnimeNavType
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.detail.ui.presenter.DetailAnimeState
import com.lelestacia.hayate.feature.anime.detail.ui.screen.AnimeDetailScreen
import com.lelestacia.hayate.feature.anime.detail.ui.util.Constant
import com.lelestacia.hayate.feature.anime.detail.ui.viewmodel.DetailViewModel
import javax.inject.Inject

class DetailFeatureImplementation @Inject constructor() : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        state: HayateState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        navGraphBuilder.composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(Constant.KEY_DATA) {
                    type = AnimeNavType()
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        ) { navBackStackEntry ->
            val vm: DetailViewModel = hiltViewModel()
            val screenState: DetailAnimeState by vm.state.collectAsStateWithLifecycle()

            BackHandler {
                onEvent(HayateEvent.Navigate(HayateNavigationType.PopBackstack))
            }

            val anime: Anime = screenState.anime
                ?: navBackStackEntry.arguments?.parcelable<Anime>(Constant.KEY_DATA)
                ?: throw Exception("What the fuck is going on")

            LaunchedEffect(key1 = Unit) {
                val event = HayateEvent.OnDetailAnimeToolbar(
                    animeID = anime.malId,
                    trailerURL = anime.trailer.url
                )
                onEvent(event)

                vm.insertAnime(anime)
                vm.getAnimeByAnimeID(anime.malId)
            }

            AnimeDetailScreen(
                anime = anime,
                isDarkTheme = state.isDarkTheme,
                isOnWatchList = screenState.isOnWatchList,
                onEvent = vm::onEvent,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}