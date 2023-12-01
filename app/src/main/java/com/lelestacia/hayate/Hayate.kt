package com.lelestacia.hayate

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.hayate.common.theme.quickSandFamily
import com.lelestacia.hayate.component.CustomAppBar
import com.lelestacia.hayate.feature.anime.collection.ui.CollectionScreen
import com.lelestacia.hayate.feature.anime.exploration.ui.AiringAnimeScreen
import com.lelestacia.hayate.feature.anime.exploration.ui.AnimeScreen
import com.lelestacia.hayate.feature.anime.exploration.ui.DisplayType
import com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel.AiringAnimeViewModel
import com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel.AnimeViewModel
import com.lelestacia.hayate.feature.settings.ui.SettingScreen
import com.lelestacia.hayate.navigation.CustomBottomNavigation
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import timber.log.Timber
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Hayate() {
    var state by remember { mutableIntStateOf(1) }


    val navController: NavHostController = rememberNavController()
    val currentRoute by navController.currentBackStackEntryAsState()

    val date = LocalDate.now()
    val titles = listOf("Schedule", "Popular", "Airing", "Upcoming")

    val pagerState = rememberPagerState(
        pageCount = {
            titles.size
        },
        initialPage = 1
    )

    LaunchedEffect(key1 = Unit) {
        navController.currentBackStackEntryFlow.collectLatest {
            Timber.d("Current Route: ${it.destination.route}")
        }
    }

    LaunchedEffect(state) {
        pagerState.animateScrollToPage(state)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            state = pagerState.currentPage
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

            composable(Screen.Exploration.route) {

                val airingAnimeVm = hiltViewModel<AiringAnimeViewModel>()
                val airingAnimeState by airingAnimeVm.state.collectAsState()

                val currentLifeCycle = LocalLifecycleOwner.current

                LaunchedEffect(key1 = Unit) {
                    airingAnimeVm.route.onEach { route ->
                        navController.navigate(route)
                    }.launchIn(currentLifeCycle.lifecycleScope)
                }

                val vm = hiltViewModel<AnimeViewModel>()

                val airingAnimeFilterType by vm.airingAnimeType.collectAsState()


                LaunchedEffect(key1 = Unit) {
                    if (date.dayOfWeek.value != 6 || date.dayOfWeek.value != 7) {
                        val day = when (date.dayOfWeek.value) {
                            1 -> "Monday"
                            2 -> "Tuesday"
                            3 -> "Wednesday"
                            4 -> "Thursday"
                            5 -> "Friday"
                            else -> "Unknown"
                        }
                        vm.scheduleAnimeFilter.update {
                            day.lowercase()
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
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
                                            fontFamily = quickSandFamily
                                        )
                                    )
                                }
                            )
                        }
                    }

                    HorizontalPager(state = pagerState) {
                        when (it) {

                            0 -> {
                                AnimeScreen(
                                    displayType = DisplayType.Scheduled,
                                    animePaging = vm.scheduleAnime.collectAsLazyPagingItems(),
                                    animeState = vm.scheduleAnimeState,
                                    animeTypeFilter = null,
                                    onAnimeClicked = {

                                    },
                                    onEvent = {

                                    },
                                    modifier = Modifier.weight(1f),
                                )
                            }

                            1 -> {
                                AnimeScreen(
                                    displayType = DisplayType.Popular,
                                    animePaging = vm.topAnime.collectAsLazyPagingItems(),
                                    animeState = vm.topAnimeState,
                                    animeTypeFilter = null,
                                    onAnimeClicked = {

                                    },
                                    onEvent = {

                                    },
                                    modifier = Modifier.weight(1f),
                                )
                            }

                            2 -> {
                                AiringAnimeScreen(
                                    airingAnimePaging = airingAnimeVm.airingAnime.collectAsLazyPagingItems(),
                                    state = airingAnimeState,
                                    onEvent = airingAnimeVm::onEvent,
                                    modifier = Modifier.weight(1f)
                                )
                            }

                            3 -> {
                                AnimeScreen(
                                    displayType = DisplayType.Upcoming,
                                    animePaging = vm.upcomingAnime.collectAsLazyPagingItems(),
                                    animeState = vm.upcomingAnimeState,
                                    animeTypeFilter = null,
                                    onAnimeClicked = {

                                    },
                                    onEvent = {

                                    },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
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
        }
    }
}