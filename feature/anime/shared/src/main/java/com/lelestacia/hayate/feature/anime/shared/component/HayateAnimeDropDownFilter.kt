package com.lelestacia.hayate.feature.anime.shared.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.lelestacia.hayate.common.shared.R
import com.lelestacia.hayate.common.shared.component.HayateCustomDropDownItem
import com.lelestacia.hayate.common.shared.component.HayateCustomFilterChip
import com.lelestacia.hayate.common.theme.padding
import com.lelestacia.hayate.common.theme.spacing
import com.lelestacia.hayate.feature.anime.shared.filter.AnimeFilter
import com.lelestacia.hayate.feature.anime.shared.filter.AnimeRating
import com.lelestacia.hayate.feature.anime.shared.filter.AnimeType

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
) {
    val scrollState = rememberScrollState()
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing.small),
        modifier = modifier
            .horizontalScroll(scrollState, true)
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
                        id = R.string.anime_type_with_value,
                        selectedAnimeType?.name
                            ?: stringResource(id = R.string.all)
                    )
                )
                DropdownMenu(
                    expanded = isAnimeTypeOpened,
                    onDismissRequest = {
                        onAnimeTypeToggled.invoke()
                    },
                ) {
                    HayateCustomDropDownItem(
                        text = stringResource(id = R.string.all),
                        onClick = {
                            onAnimeTypeSelected(null)
                        }
                    )
                    AnimeType.entries.forEach { selectedFilter ->
                        HayateCustomDropDownItem(
                            text = selectedFilter.name,
                            onClick = {
                                onAnimeTypeSelected(selectedFilter)
                            }
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
                        id = R.string.anime_filter_with_value,
                        selectedAnimeFilter?.title
                            ?: stringResource(id = R.string.all)
                    )
                )
                DropdownMenu(
                    expanded = isAnimeFilterOpened,
                    onDismissRequest = {
                        onAnimeFilterToggled?.invoke()
                    },
                ) {
                    HayateCustomDropDownItem(
                        text = stringResource(id = R.string.all),
                        onClick = {
                            if (onAnimeFilterSelected != null) {
                                onAnimeFilterSelected(null)
                            }
                        }
                    )
                    AnimeFilter.entries.forEach { selectedFilter ->
                        HayateCustomDropDownItem(
                            text = selectedFilter.title,
                            onClick = {
                                if (onAnimeFilterSelected != null) {
                                    onAnimeFilterSelected(selectedFilter)
                                }
                            }
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
                        id = R.string.anime_rating_with_value,
                        selectedAnimeRating?.title
                            ?: stringResource(id = R.string.all)
                    )
                )
                DropdownMenu(
                    expanded = isAnimeRatingOpened,
                    onDismissRequest = {
                        onAnimeRatingToggled?.invoke()
                    },
                ) {
                    HayateCustomDropDownItem(
                        text = stringResource(id = R.string.all),
                        onClick = {
                            if (onAnimeRatingSelected != null) {
                                onAnimeRatingSelected(null)
                            }
                        }
                    )
                    AnimeRating.entries.forEach { selectedFilter ->
                        HayateCustomDropDownItem(
                            text = selectedFilter.title,
                            onClick = {
                                if (onAnimeRatingSelected != null) {
                                    onAnimeRatingSelected(selectedFilter)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHayateAnimeDropDownFilter() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding.small)
            )
        }
    }
}