package com.lelestacia.hayate.feature.anime.core.common.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.lelestacia.hayate.core.common.screen.ErrorScreen
import com.lelestacia.hayate.core.common.screen.LoadingScreen
import com.lelestacia.hayate.core.theme.quickSandFamily
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchAnimePaging(
    query: String,
    state: LazyGridState,
    isDarkTheme: Boolean,
    animePaging: LazyPagingItems<Anime>,
    onClick: (Anime) -> Unit,
    modifier: Modifier = Modifier,
    shouldUseKey: Boolean = false,
) {
    when (animePaging.loadState.refresh) {
        is LoadState.Error -> {
            ErrorScreen(
                errorMessage = (animePaging.loadState.refresh as LoadState.Error).error.message.orEmpty(),
                onRetry = animePaging::retry,
                isDarkTheme = isDarkTheme,
                modifier = Modifier.fillMaxSize()
            )
        }

        LoadState.Loading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }

        is LoadState.NotLoading -> {
            if (animePaging.itemCount == 0) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "No result found for $query",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontFamily = quickSandFamily,
                            color = when (isDarkTheme) {
                                true -> Color.White
                                false -> Color.Black
                            }
                        )
                    )
                }
            } else {
                LazyVerticalGrid(
                    state = state,
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(
                        bottom = 8.dp,
                        start = 8.dp,
                        end = 8.dp,
                        top = 8.dp
                    ),
                    modifier = modifier
                ) {

                    items(
                        count = animePaging.itemCount,
                        contentType = animePaging.itemContentType(),
                        key =
                        when (shouldUseKey) {
                            true -> animePaging.itemKey { anime ->
                                anime.malId
                            }

                            false -> null
                        },
                    ) { index ->
                        val currentAnime: Anime? = animePaging[index]
                        currentAnime?.let { validAnime ->
                            AnimeCard(
                                anime = validAnime,
                                onClick = onClick,
                                isDarkTheme = isDarkTheme,
                                modifier = Modifier.animateItemPlacement()
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
                                    isDarkTheme = isDarkTheme,
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
}