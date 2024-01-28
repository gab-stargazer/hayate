package com.lelestacia.hayate.feature.anime.exploration.ui.di

import android.net.Uri
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.hayate.core.common.Screen
import com.lelestacia.hayate.core.common.api.FeatureApi
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.event.HayateNavigationType
import com.lelestacia.hayate.core.common.screen.DisableScreen
import com.lelestacia.hayate.core.common.state.HayateState
import com.lelestacia.hayate.core.common.util.toJson
import com.lelestacia.hayate.core.theme.quickSandFamily
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.ui.R
import com.lelestacia.hayate.feature.anime.exploration.ui.screen.AiringAnimeScreen
import com.lelestacia.hayate.feature.anime.exploration.ui.screen.PopularAnimeScreen
import com.lelestacia.hayate.feature.anime.exploration.ui.screen.ScheduleAnimeScreen
import com.lelestacia.hayate.feature.anime.exploration.ui.screen.UpcomingAnimeScreen
import com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel.AiringViewModel
import com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel.PopularViewModel
import com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel.ScheduleViewModel
import com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel.UpcomingViewModel
import javax.inject.Inject

internal class ExploreFeatureImplementation @Inject constructor() : FeatureApi {

    @OptIn(ExperimentalFoundationApi::class)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        state: HayateState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        navGraphBuilder.composable(
            route = Screen.Exploration.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.Init.route -> slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Screen.Detail.route -> fadeOut()
                    Screen.Collection.route -> slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    Screen.More.route -> slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    Screen.Search.route -> slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) + fadeOut()
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Screen.Collection.route -> slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    Screen.More.route -> slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    Screen.Detail.route -> fadeIn()
                    Screen.Search.route -> slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) + fadeIn()
                    else -> null
                }
            },
        ) {
            val titles = stringArrayResource(id = R.array.pager_title)
            var selectedTabIndex by rememberSaveable { mutableIntStateOf(1) }

            val pagerState = rememberPagerState(
                pageCount = {
                    titles.size
                },
                initialPage = 1
            )

            LaunchedEffect(selectedTabIndex) {
                pagerState.animateScrollToPage(selectedTabIndex)
            }

            LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                if (!pagerState.isScrollInProgress) {
                    selectedTabIndex = pagerState.currentPage
                }
            }

            val popularAnimeVm = hiltViewModel<PopularViewModel>()
            val popularAnimeState by popularAnimeVm.state.collectAsStateWithLifecycle()

            val airingAnimeVm = hiltViewModel<AiringViewModel>()
            val airingAnimeState by airingAnimeVm.state.collectAsStateWithLifecycle()

            val upcomingAnimeVm = hiltViewModel<UpcomingViewModel>()
            val upcomingAnimeState by upcomingAnimeVm.state.collectAsStateWithLifecycle()
            val isUpcomingFeatureEnabled by upcomingAnimeVm.isFeatureEnabled.collectAsStateWithLifecycle()

            val scheduleAnimeVm = hiltViewModel<ScheduleViewModel>()
            val scheduleAnimeState by scheduleAnimeVm.state.collectAsStateWithLifecycle()
            val isScheduleFeatureEnabled by scheduleAnimeVm.isFeatureEnabled.collectAsStateWithLifecycle()


            val handleAnimeClicked: (Anime) -> Unit = { clickedAnime ->
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

                //  navController.navigate(
                //      Screen.Detail.createRoute(
                //          jsonAnime = Uri.encode(jsonAnime)
                //  )
                //      ) {
                //          launchSingleTop = true
                //      }

                popularAnimeVm.insertAnime(clickedAnime)
            }

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = quickSandFamily,
                                    ),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )
                            }
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    userScrollEnabled = false
                ) { index ->
                    when (index) {

                        0 -> {
                            if (isScheduleFeatureEnabled) {
                                ScheduleAnimeScreen(
                                    scheduledAnimePaging = scheduleAnimeVm.scheduledAnime.collectAsLazyPagingItems(),
                                    state = scheduleAnimeState,
                                    isDarkTheme = state.isDarkTheme,
                                    onAnimeClicked = handleAnimeClicked,
                                    modifier = Modifier.weight(1f),
                                )
                            } else {
                                DisableScreen(
                                    isDarkTheme = isSystemInDarkTheme(),
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }

                        1 -> {
                            PopularAnimeScreen(
                                popularAnimePaging = popularAnimeVm.popularAnime.collectAsLazyPagingItems(),
                                state = popularAnimeState,
                                isDarkTheme = state.isDarkTheme,
                                onEvent = popularAnimeVm::onEvent,
                                onAnimeClicked = handleAnimeClicked,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        2 -> {
                            AiringAnimeScreen(
                                airingAnimePaging = airingAnimeVm.airingAnime.collectAsLazyPagingItems(),
                                state = airingAnimeState,
                                isDarkTheme = state.isDarkTheme,
                                onEvent = airingAnimeVm::onEvent,
                                onAnimeClicked = handleAnimeClicked,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        3 -> {
                            if (isUpcomingFeatureEnabled) {
                                UpcomingAnimeScreen(
                                    airingAnimePaging = upcomingAnimeVm.upcomingAnime.collectAsLazyPagingItems(),
                                    state = upcomingAnimeState,
                                    isDarkTheme = state.isDarkTheme,
                                    onEvent = upcomingAnimeVm::onEvent,
                                    onAnimeClicked = handleAnimeClicked,
                                    modifier = Modifier.weight(1f)
                                )
                            } else {
                                DisableScreen(
                                    isDarkTheme = isSystemInDarkTheme(),
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}