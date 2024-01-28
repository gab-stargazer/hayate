package com.lelestacia.hayate.feature.anime.collection.ui.di

import android.net.Uri
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.lelestacia.hayate.core.common.state.HayateState
import com.lelestacia.hayate.core.common.util.toJson
import com.lelestacia.hayate.core.theme.quickSandFamily
import com.lelestacia.hayate.feature.anime.collection.ui.R
import com.lelestacia.hayate.feature.anime.collection.ui.viewmodel.CollectionViewModel
import com.lelestacia.hayate.feature.anime.core.common.component.AnimePagingLazyGrid
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import javax.inject.Inject

internal class CollectionFeatureImplementation @Inject constructor() : FeatureApi {

    @OptIn(ExperimentalFoundationApi::class)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        state: HayateState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        navGraphBuilder.composable(
            route = Screen.Collection.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.Exploration.route -> slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                    Screen.More.route -> slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
                    else -> fadeIn()
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Screen.Exploration.route -> slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
                    Screen.More.route -> slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                    else -> fadeOut()
                }
            },
            popExitTransition = {
                when (initialState.destination.route) {
                    Screen.Exploration.route -> slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
                    Screen.More.route -> slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                    else -> fadeOut()
                }
            }
        ) {
            val vm = hiltViewModel<CollectionViewModel>()
            val screenState by vm.state.collectAsStateWithLifecycle()
            val handleOnClick: (Anime) -> Unit = { clickedAnime ->
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

                //  TODO: Fix this later so anime list should be updated based on correct order
                //      Fixed but seems theres a better way to do this, so i'll do it later
            }

            val titles = stringArrayResource(id = R.array.collection_pager_title)
            var selectedTitleIndex by rememberSaveable { mutableIntStateOf(0) }
            val pagerState = rememberPagerState(
                pageCount = {
                    titles.size
                },
                initialPage = selectedTitleIndex
            )

            LaunchedEffect(selectedTitleIndex) {
                pagerState.animateScrollToPage(selectedTitleIndex)
            }

            LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                if (!pagerState.isScrollInProgress) {
                    selectedTitleIndex = pagerState.currentPage
                }
            }

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TabRow(
                    selectedTabIndex = selectedTitleIndex,
                    containerColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTitleIndex == index,
                            onClick = { selectedTitleIndex = index },
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
                            AnimePagingLazyGrid(
                                animePaging = vm.animeHistory.collectAsLazyPagingItems(),
                                isDarkTheme = state.isDarkTheme,
                                shouldUseKey = true,
                                state = screenState.historyGridState,
                                onClick = { anime ->
                                    handleOnClick(anime)
                                },
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        1 -> {
                            AnimePagingLazyGrid(
                                animePaging = vm.animeWatchList.collectAsLazyPagingItems(),
                                isDarkTheme = state.isDarkTheme,
                                shouldUseKey = true,
                                state = screenState.watchListGridState,
                                onClick = { anime ->
                                    handleOnClick(anime)
                                },
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}