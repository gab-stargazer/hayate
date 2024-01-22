package com.lelestacia.hayate.feature.anime.detail.ui.di

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.api.FeatureApi
import com.lelestacia.hayate.common.shared.event.HayateEvent
import com.lelestacia.hayate.common.shared.util.parcelable
import com.lelestacia.hayate.feature.anime.core.common.parcelable.AnimeNavType
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.detail.ui.screen.AnimeDetailScreen
import com.lelestacia.hayate.feature.anime.detail.ui.util.Constant.KEY_DATA
import com.lelestacia.hayate.feature.anime.detail.ui.viewmodel.DetailViewModel

internal object InternalDetailModuleAPI : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        navGraphBuilder.composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(KEY_DATA) {
                    type = AnimeNavType()
                }
            ),
            enterTransition = {
                return@composable slideIntoContainer(
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
                return@composable slideOutOfContainer(
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
            val state by vm.state.collectAsStateWithLifecycle()

            val anime: Anime = state.anime
                ?: navBackStackEntry.arguments?.parcelable<Anime>(KEY_DATA)
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
                isOnWatchList = state.isOnWatchList,
                onEvent = vm::onEvent,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}