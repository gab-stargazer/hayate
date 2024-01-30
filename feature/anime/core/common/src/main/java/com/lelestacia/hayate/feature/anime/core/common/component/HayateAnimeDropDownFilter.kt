package com.lelestacia.hayate.feature.anime.core.common.component

import android.content.res.Configuration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.hayate.core.common.component.HayateCustomDropDownItem
import com.lelestacia.hayate.core.common.component.HayateCustomFilterChip
import com.lelestacia.hayate.core.theme.AppTheme
import com.lelestacia.hayate.core.theme.padding
import com.lelestacia.hayate.core.theme.spacing
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeFilter
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeRating
import com.lelestacia.hayate.feature.anime.core.common.filter.AnimeType

/**
 * Composable function to create a dropdown filter interface for anime-related content.
 *
 * @param modifier Modifier to be applied to the layout.
 * @param isAnimeTypeOpened Boolean representing whether the anime type dropdown is expanded or not.
 * @param shouldAnimeTypeBeVisible Boolean indicating whether the anime type filter should be visible.
 * @param selectedAnimeType The currently selected anime type.
 * @param onAnimeTypeSelected Callback function for when an anime type is selected.
 * @param onAnimeTypeToggled Callback function for toggling the visibility of the anime type dropdown.
 * @param isAnimeFilterOpened Boolean representing whether the anime filter dropdown is expanded or not.
 * @param shouldAnimeFilterBeVisible Boolean indicating whether the anime filter should be visible.
 * @param selectedAnimeFilter The currently selected anime filter.
 * @param onAnimeFilterSelected Callback function for when an anime filter is selected.
 * @param onAnimeFilterToggled Callback function for toggling the visibility of the anime filter dropdown.
 * @param isAnimeRatingOpened Boolean representing whether the anime rating dropdown is expanded or not.
 * @param shouldAnimeRatingBeVisible Boolean indicating whether the anime rating should be visible.
 * @param selectedAnimeRating The currently selected anime rating.
 * @param onAnimeRatingSelected Callback function for when an anime rating is selected.
 * @param onAnimeRatingToggled Callback function for toggling the visibility of the anime rating dropdown.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HayateAnimeDropDownFilter(
    modifier: Modifier = Modifier,
    isAnimeTypeOpened: Boolean,
    shouldAnimeTypeBeVisible: Boolean,
    selectedAnimeType: AnimeType?,
    onAnimeTypeSelected: (AnimeType?) -> Unit,
    onAnimeTypeToggled: () -> Unit,
    isAnimeFilterOpened: Boolean = false,
    shouldAnimeFilterBeVisible: Boolean = false,
    selectedAnimeFilter: AnimeFilter? = null,
    onAnimeFilterSelected: ((AnimeFilter?) -> Unit)? = null,
    onAnimeFilterToggled: (() -> Unit)? = null,
    isAnimeRatingOpened: Boolean = false,
    shouldAnimeRatingBeVisible: Boolean = false,
    selectedAnimeRating: AnimeRating? = null,
    onAnimeRatingSelected: ((AnimeRating?) -> Unit)? = null,
    onAnimeRatingToggled: (() -> Unit)? = null,
    isDarkTheme: Boolean,
) {
    val scrollState = rememberScrollState()
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing.small),
        modifier = modifier
            .horizontalScroll(scrollState, true)
            .semantics {
                testTagsAsResourceId = true
            }
    ) {
        if (shouldAnimeTypeBeVisible) {
            Column {
                HayateCustomFilterChip(
                    isActive = isAnimeTypeOpened,
                    isSelected = selectedAnimeType != null,
                    onClick = {
                        onAnimeTypeToggled.invoke()
                    },
                    text = stringResource(
                        id = com.lelestacia.hayate.core.common.R.string.anime_type_with_value,
                        selectedAnimeType?.name
                            ?: stringResource(id = com.lelestacia.hayate.core.common.R.string.all)
                    ),
                    modifier = Modifier.testTag("button:filter:type")
                )
                DropdownMenu(
                    expanded = isAnimeTypeOpened,
                    onDismissRequest = {
                        onAnimeTypeToggled.invoke()
                    },
                ) {
                    HayateCustomDropDownItem(
                        text = stringResource(id = com.lelestacia.hayate.core.common.R.string.all),
                        isDarkTheme = isDarkTheme,
                        onClick = {
                            onAnimeTypeSelected(null)
                        }
                    )
                    AnimeType.entries.forEach { selectedFilter ->
                        HayateCustomDropDownItem(
                            text = selectedFilter.name,
                            isDarkTheme = isDarkTheme,
                            onClick = {
                                onAnimeTypeSelected(selectedFilter)
                            },
                            modifier = Modifier
                                .testTag("dropdown:item:${selectedFilter.name.lowercase()}")
                        )
                    }
                }
            }
        }

        if (shouldAnimeFilterBeVisible) {
            Column {
                HayateCustomFilterChip(
                    isActive = isAnimeFilterOpened,
                    isSelected = selectedAnimeFilter != null,
                    onClick = {
                        onAnimeFilterToggled?.invoke()
                    },
                    text = stringResource(
                        id = com.lelestacia.hayate.core.common.R.string.anime_filter_with_value,
                        selectedAnimeFilter?.title
                            ?: stringResource(id = com.lelestacia.hayate.core.common.R.string.all)
                    ),
                    modifier = Modifier.testTag("button:filter:filter")
                )
                DropdownMenu(
                    expanded = isAnimeFilterOpened,
                    onDismissRequest = {
                        onAnimeFilterToggled?.invoke()
                    },
                ) {
                    HayateCustomDropDownItem(
                        text = stringResource(id = com.lelestacia.hayate.core.common.R.string.all),
                        isDarkTheme = isDarkTheme,
                        onClick = {
                            if (onAnimeFilterSelected != null) {
                                onAnimeFilterSelected(null)
                            }
                        }
                    )
                    AnimeFilter.entries.forEach { selectedFilter ->
                        HayateCustomDropDownItem(
                            text = selectedFilter.title,
                            isDarkTheme = isDarkTheme,
                            onClick = {
                                if (onAnimeFilterSelected != null) {
                                    onAnimeFilterSelected(selectedFilter)
                                }
                            },
                            modifier = Modifier.testTag("dropdown:item:${selectedFilter.query}")
                        )
                    }
                }
            }
        }

        if (shouldAnimeRatingBeVisible) {
            Column {
                HayateCustomFilterChip(
                    isActive = isAnimeRatingOpened,
                    isSelected = selectedAnimeRating != null,
                    onClick = {
                        onAnimeRatingToggled?.invoke()
                    },
                    text = stringResource(
                        id = com.lelestacia.hayate.core.common.R.string.anime_rating_with_value,
                        selectedAnimeRating?.title
                            ?: stringResource(id = com.lelestacia.hayate.core.common.R.string.all)
                    ),
                    modifier = Modifier.testTag("button:filter:rating")
                )
                DropdownMenu(
                    expanded = isAnimeRatingOpened,
                    onDismissRequest = {
                        onAnimeRatingToggled?.invoke()
                    },
                ) {
                    HayateCustomDropDownItem(
                        text = stringResource(id = com.lelestacia.hayate.core.common.R.string.all),
                        isDarkTheme = isDarkTheme,
                        onClick = {
                            if (onAnimeRatingSelected != null) {
                                onAnimeRatingSelected(null)
                            }
                        }
                    )
                    AnimeRating.entries.forEach { selectedFilter ->
                        HayateCustomDropDownItem(
                            text = selectedFilter.title,
                            isDarkTheme = isDarkTheme,
                            onClick = {
                                if (onAnimeRatingSelected != null) {
                                    onAnimeRatingSelected(selectedFilter)
                                }
                            },
                            modifier = Modifier.testTag("dropdown:item:${selectedFilter.query}")
                        )
                    }
                }
            }
        }
    }
}

@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Day Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewDropDownFilter() {
    AppTheme(
        dynamicColor = false
    ) {
        Surface(
            modifier = Modifier.padding(padding.medium)
        ) {

            var selectedFilterType: AnimeType? by remember {
                mutableStateOf(null)
            }

            var isFilterTypeOpened by remember {
                mutableStateOf(false)
            }

            var selectedAnimeFilter: AnimeFilter? by remember {
                mutableStateOf(AnimeFilter.AIRING)
            }

            var isAnimeFilterOpened by remember {
                mutableStateOf(false)
            }

            HayateAnimeDropDownFilter(
                isAnimeTypeOpened = isFilterTypeOpened,
                shouldAnimeTypeBeVisible = true,
                selectedAnimeType = selectedFilterType,
                onAnimeTypeSelected = {
                    selectedFilterType = it
                },
                onAnimeTypeToggled = {
                    isFilterTypeOpened = !isFilterTypeOpened
                },
                isAnimeFilterOpened = isAnimeFilterOpened,
                shouldAnimeFilterBeVisible = true,
                selectedAnimeFilter = selectedAnimeFilter,
                onAnimeFilterSelected = {
                    selectedAnimeFilter = it
                },
                onAnimeFilterToggled = {
                    isAnimeFilterOpened = !isAnimeFilterOpened
                },
                isDarkTheme = isSystemInDarkTheme(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding.small)
            )
        }
    }
}