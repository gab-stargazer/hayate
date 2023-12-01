package com.lelestacia.hayate.feature.anime.exploration.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.lelestacia.hayate.common.shared.LoadingScreen
import com.lelestacia.hayate.common.theme.padding
import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.ui.component.AnimeCard
import com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel.AnimeScreenEvent
import com.lelestacia.hayate.feature.anime.shared.AnimeTypeFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun AnimeScreen(
    displayType: DisplayType,
    animePaging: LazyPagingItems<Anime>,
    animeTypeFilter: AnimeTypeFilter?,
    animeState: LazyGridState,
    onAnimeClicked: (Anime) -> Unit,
    onEvent: (AnimeScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    Scaffold(
        floatingActionButton = {
//            if (displayType != DisplayType.Scheduled) {
//                SmallFloatingActionButton(onClick = {
//                    showBottomSheet = true
//                }) {
//                    Icon(
//                        imageVector = Icons.Default.FilterList,
//                        contentDescription = null
//                    )
//                }
//            }
        }
    ) {

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                ) {
//                    TODO: Implement Bottom Sheet for filtering Anime
                }
            }
        }

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

            if (displayType == DisplayType.Airing) {
                var isDropDownMenuOpened by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = padding.small, vertical = padding.extraSmall)
                ) {
                    FilterChip(
                        selected = animeTypeFilter != null,
                        onClick = {
                            isDropDownMenuOpened = true
                        },
                        label = {
                            Text(
                                text = stringResource(
                                    id = com.lelestacia.hayate.common.shared.R.string.anime_type_with_value,
                                    animeTypeFilter?.name
                                        ?: stringResource(id = com.lelestacia.hayate.common.shared.R.string.all)
                                )
                            )
                        },
                        trailingIcon = {
                            AnimatedContent(
                                targetState = isDropDownMenuOpened,
                                label = "Chip Trailing Icon Animation"
                            ) { isOpened ->
                                when (isOpened) {
                                    true -> Icon(
                                        imageVector = Icons.Default.ArrowDropUp,

                                        contentDescription = null
                                    )

                                    false -> Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = null
                                    )
                                }
                            }
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.Transparent,
                            labelColor = MaterialTheme.colorScheme.primary,
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTrailingIconColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        shape = RoundedCornerShape(15),
                        border = FilterChipDefaults.filterChipBorder(
                            selectedBorderColor = Color.Transparent,
                            borderColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.animateContentSize()
                    )
                    DropdownMenu(
                        expanded = isDropDownMenuOpened,
                        onDismissRequest = {
                            isDropDownMenuOpened = false
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = "All")
                            }, onClick = {
                                val event = AnimeScreenEvent.AiringAnimeEvent.OnTypeChanged(
                                    filter = null
                                )
                                onEvent(event)
                            }
                        )
                        AnimeTypeFilter.entries.forEach {
                            DropdownMenuItem(
                                text = {
                                    Text(text = it.name)
                                }, onClick = {
                                    val event = AnimeScreenEvent.AiringAnimeEvent.OnTypeChanged(
                                        filter = it
                                    )
                                    onEvent(event)
                                }
                            )
                        }
                    }
                }
            }


            when (animePaging.loadState.refresh) {
                is LoadState.Error -> Unit
                LoadState.Loading -> {
                    LoadingScreen(modifier = modifier.fillMaxSize())
                }

                is LoadState.NotLoading -> {
                    LazyVerticalGrid(
                        state = animeState,
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {

                        items(count = animePaging.itemCount) { index ->
                            val currentAnime: Anime? = animePaging[index]
                            currentAnime?.let { validAnime ->
                                AnimeCard(
                                    anime = validAnime,
                                    onClicked = onAnimeClicked,
                                    isDarkTheme = isSystemInDarkTheme()
                                )
                            }
                        }

                        when (animePaging.loadState.append) {
                            is LoadState.Error -> Unit
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
}