package com.lelestacia.hayate.feature.anime.detail.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lelestacia.hayate.core.common.component.HayateCustomChip
import com.lelestacia.hayate.core.theme.padding
import com.lelestacia.hayate.core.theme.quickSandFamily
import com.lelestacia.hayate.core.theme.spacing
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.detail.ui.component.AnimeHeader
import com.lelestacia.hayate.feature.anime.detail.ui.component.AnimeInformation
import com.lelestacia.hayate.feature.anime.detail.ui.component.CardSection
import com.lelestacia.hayate.feature.anime.detail.ui.presenter.DetailAnimeEvent
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Bookmark
import compose.icons.fontawesomeicons.solid.Bookmark

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AnimeDetailScreen(
    anime: Anime,
    isOnWatchList: Boolean,
    onEvent: (DetailAnimeEvent) -> Unit,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
) {

    //  TODO: Try to remember what is this for
    val state = rememberModalBottomSheetState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(DetailAnimeEvent.InsertWatchList(anime.malId))
                }
            ) {
                Icon(
                    imageVector =
                    when (isOnWatchList) {
                        true -> FontAwesomeIcons.Solid.Bookmark
                        false -> FontAwesomeIcons.Regular.Bookmark
                    },
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.small),
            modifier = Modifier
                .padding(paddingValues)
        ) {

            var isSynopsisExpanded by rememberSaveable {
                mutableStateOf(false)
            }

            if (isSynopsisExpanded) {
                ModalBottomSheet(
                    onDismissRequest = {
                        isSynopsisExpanded = false
                    },
                    sheetState = state,
                    dragHandle = { BottomSheetDefaults.DragHandle() },
                ) {
                    Text(
                        text = anime.synopsis.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = quickSandFamily,
                            fontWeight = FontWeight.Normal,
                            color =
                            when (isDarkTheme) {
                                true -> Color.White
                                false -> MaterialTheme.colorScheme.onSurface
                            }
                        ),
                        modifier = Modifier
                            .verticalScroll(
                                state = rememberScrollState(),
                                enabled = true
                            )
                            .padding(
                                horizontal = padding.medium
                            )
                            .padding(
                                bottom = padding.extraLarge,
                                top = padding.medium
                            )
                    )
                }
            }

            AnimeHeader(
                malID = anime.malId,
                coverImages = anime.images.webp.largeImageUrl,
                title = anime.title,
                titleJapanese = anime.titleJapanese,
                rank = anime.rank ?: 0,
                score = anime.score,
                scoredBy = anime.scoredBy,
                status = anime.status,
                isDarkTheme = isDarkTheme
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = padding.medium),
                horizontalArrangement = Arrangement.spacedBy(spacing.small)
            ) {

                items(count = anime.demographics.size) { index ->
                    val demographic = anime.demographics[index]
                    HayateCustomChip(
                        text = demographic.name,
                        onClick = {
                            // TODO: Implement this later
                        }
                    )
                }

                items(count = anime.genres.size) { index ->
                    val genre = anime.genres[index]
                    HayateCustomChip(
                        text = genre.name,
                        onClick = {
                            // TODO: Implement this later
                        }
                    )
                }

//            items(count = anime.themes.size) { index ->
//                val theme = anime.themes[index]
//                HayateCustomChip(
//                    text = theme.name,
//                    onClick = {
//                        // TODO: Implement this later
//                    }
//                )
//            }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.small),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = anime.synopsis.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = quickSandFamily,
                        fontWeight = FontWeight.Normal,
                        color =
                        when (isDarkTheme) {
                            true -> Color.White
                            false -> MaterialTheme.colorScheme.onSurface
                        }
                    ),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = padding.medium
                        )
                )

                TextButton(
                    onClick = {
                        isSynopsisExpanded = true
                    },
                    contentPadding = PaddingValues(
                        horizontal = padding.medium,
                        vertical = 0.dp
                    )
                ) {
                    Text(
                        text = "Read More",
                        textDecoration = TextDecoration.Underline,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontFamily = quickSandFamily,
                            fontWeight = FontWeight.Bold,
                            color =
                            when (isDarkTheme) {
                                true -> Color.White
                                false -> MaterialTheme.colorScheme.onSurface
                            }
                        ),
                    )
                }
            }

            CardSection(
                content = {
                    AnimeInformation(
                        anime = anime,
                        isDarkTheme = true
                    )
                },
                isDarkTheme = isSystemInDarkTheme()
            )
        }
    }
}