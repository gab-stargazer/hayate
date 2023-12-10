package com.lelestacia.hayate.feature.anime.core.common.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.lelestacia.hayate.common.shared.screen.ErrorScreen
import com.lelestacia.hayate.common.shared.screen.LoadingScreen
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime

@Composable
fun AnimePagingLazyGrid(
    state: LazyGridState,
    animePaging: LazyPagingItems<Anime>,
    onClick: (Anime) -> Unit,
    modifier: Modifier = Modifier
) {
    when (animePaging.loadState.refresh) {
        is LoadState.Error -> {
            ErrorScreen(
                errorMessage = (animePaging.loadState.refresh as LoadState.Error).error.message.orEmpty(),
                onRetry = {
                    animePaging.retry()
                },
                isDarkTheme = isSystemInDarkTheme(),
                modifier = Modifier.fillMaxSize()
            )
        }

        LoadState.Loading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }

        is LoadState.NotLoading -> {
            LazyVerticalGrid(
                state = state,
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(
                    bottom = 8.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
                modifier = modifier
            ) {

                items(count = animePaging.itemCount) { index ->
                    val currentAnime: Anime? = animePaging[index]
                    currentAnime?.let { validAnime ->
                        AnimeCard(
                            anime = validAnime,
                            onClick = onClick,
                            isDarkTheme = isSystemInDarkTheme()
                        )
                    }
                }

                when (animePaging.loadState.append) {
                    is LoadState.Error -> {
                        item(
                            span = {
                                GridItemSpan(3)
                            }
                        ) {
                            ErrorScreen(
                                errorMessage = (animePaging.loadState.refresh as LoadState.Error).error.message.orEmpty(),
                                onRetry = {
                                    animePaging.retry()
                                },
                                isDarkTheme = isSystemInDarkTheme(),
                                modifier = Modifier
                                    .height(250.dp)
                                    .fillMaxWidth()
                            )
                        }
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