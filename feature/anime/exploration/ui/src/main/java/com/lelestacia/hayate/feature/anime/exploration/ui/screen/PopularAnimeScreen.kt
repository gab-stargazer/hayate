package com.lelestacia.hayate.feature.anime.exploration.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.lelestacia.hayate.common.shared.LoadingScreen
import com.lelestacia.hayate.common.theme.padding
import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular.PopularAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.popular.PopularAnimeState
import com.lelestacia.hayate.feature.anime.exploration.ui.component.AnimeCard
import com.lelestacia.hayate.feature.anime.shared.HayateAnimeDropDownFilter

@Composable
fun PopularAnimeScreen(
    popularAnimePaging: LazyPagingItems<Anime>,
    state: PopularAnimeState,
    onEvent: (PopularAnimeEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
                if (isSystemInDarkTheme()) {
                    Color.Black
                } else {
                    Color.White
                }
            )
    ) {

        HayateAnimeDropDownFilter(
            shouldFilterTypeBeVisible = true,
            isFilterTypeOpened = state.isTypeMenuOpened,
            selectedFilterType = state.animeType,
            onFilterTypeSelected = { selectedType ->
                onEvent(PopularAnimeEvent.OnAnimeTypeChanged(selectedType))
            },
            onFilterTypeToggled = {
                onEvent(PopularAnimeEvent.OnTypeMenuToggled)
            },
            shouldAnimeFilterBeVisible = true,
            isAnimeFilterOpened = state.isFilterMenuOpened,
            selectedAnimeFilter = state.animeFilter,
            onAnimeFilterSelected = { selectedFilter ->
                onEvent(PopularAnimeEvent.OnAnimeFilterChanged(selectedFilter))
            },
            onAnimeFilterToggled = {
                onEvent(PopularAnimeEvent.OnFilterMenuToggled)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.small, vertical = padding.extraSmall)
        )

        when (popularAnimePaging.loadState.refresh) {
            is LoadState.Error -> {
                // TODO: Implement Error Screen
            }

            LoadState.Loading -> {
                LoadingScreen(modifier = modifier.fillMaxSize())
            }

            is LoadState.NotLoading -> {
                LazyVerticalGrid(
                    state = state.gridState,
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(
                        bottom = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    ),
                    modifier = Modifier.weight(1f)
                ) {

                    items(count = popularAnimePaging.itemCount) { index ->
                        val currentAnime: Anime? = popularAnimePaging[index]
                        currentAnime?.let { validAnime ->
                            AnimeCard(
                                anime = validAnime,
                                onClicked = { clickedAnime ->
                                    val event = PopularAnimeEvent.OnAnimeClicked(clickedAnime)
                                    onEvent(event)
                                },
                                isDarkTheme = isSystemInDarkTheme()
                            )
                        }
                    }

                    when (popularAnimePaging.loadState.append) {
                        is LoadState.Error -> {
                            // TODO: Implement Error Screen
                        }

                        LoadState.Loading -> {
                            item(
                                span = {
                                    GridItemSpan(3)
                                }
                            ) {
                                LoadingScreen(
                                    modifier = Modifier
                                        .height(250.dp)
                                        .fillMaxWidth()
                                )
                            }
                        }

                        is LoadState.NotLoading -> Unit
                    }
                }
            }
        }
    }
}