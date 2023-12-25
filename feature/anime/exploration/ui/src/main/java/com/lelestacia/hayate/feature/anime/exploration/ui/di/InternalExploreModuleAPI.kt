package com.lelestacia.hayate.feature.anime.exploration.ui.di

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.api.FeatureApi
import com.lelestacia.hayate.common.shared.util.toJson
import com.lelestacia.hayate.common.theme.quickSandFamily
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
import kotlinx.coroutines.launch

internal object InternalExploreModuleAPI : FeatureApi {

    /*
     *  NOTE: Gesture temporarily disabled due to inconsistency when user is scrolling
     *  the filter menu
     */

    @OptIn(ExperimentalFoundationApi::class)
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackBarHostState: SnackbarHostState
    ) {
        navGraphBuilder.composable(
            route = Screen.Exploration.route
        ) {
            val titles = stringArrayResource(id = R.array.pager_title)
            var state by rememberSaveable { mutableIntStateOf(1) }
            val scope = rememberCoroutineScope()

            val pagerState = rememberPagerState(
                pageCount = {
                    titles.size
                },
                initialPage = 1
            )

            LaunchedEffect(state) {
                pagerState.animateScrollToPage(state)
            }

            LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                if (!pagerState.isScrollInProgress) {
                    state = pagerState.currentPage
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
                val jsonAnime = toJson(clickedAnime)
                navController.navigate(
                    Screen.Detail.createRoute(
                        jsonAnime = Uri.encode(jsonAnime)
                    )
                ) {
                    launchSingleTop = true
                }

                scope.launch {
                    popularAnimeVm.insertAnime(clickedAnime)
                }
            }

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TabRow(
                    selectedTabIndex = state,
                    containerColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = state == index,
                            onClick = { state = index },
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
                                    onAnimeClicked = handleAnimeClicked,
                                    modifier = Modifier.weight(1f),
                                )
                            } else {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(text = "Feature is temporarily disabled or not available")
                                }
                            }
                        }

                        1 -> {
                            PopularAnimeScreen(
                                popularAnimePaging = popularAnimeVm.popularAnime.collectAsLazyPagingItems(),
                                state = popularAnimeState,
                                onEvent = popularAnimeVm::onEvent,
                                onAnimeClicked = handleAnimeClicked,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        2 -> {
                            AiringAnimeScreen(
                                airingAnimePaging = airingAnimeVm.airingAnime.collectAsLazyPagingItems(),
                                state = airingAnimeState,
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
                                    onEvent = upcomingAnimeVm::onEvent,
                                    onAnimeClicked = handleAnimeClicked,
                                    modifier = Modifier.weight(1f)
                                )
                            } else {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(text = "Feature is temporarily disabled or not available")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}