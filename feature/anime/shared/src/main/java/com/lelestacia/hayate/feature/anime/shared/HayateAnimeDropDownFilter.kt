package com.lelestacia.hayate.feature.anime.shared

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.lelestacia.hayate.common.shared.HayateCustomDropDownItem
import com.lelestacia.hayate.common.theme.padding
import com.lelestacia.hayate.common.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HayateAnimeDropDownFilter(
    modifier: Modifier = Modifier,
    isFilterTypeOpened: Boolean,
    shouldFilterTypeBeVisible: Boolean,
    selectedFilterType: AnimeType?,
    onFilterTypeSelected: (AnimeType?) -> Unit,
    onFilterTypeToggled: () -> Unit,
    isAnimeFilterOpened: Boolean = false,
    shouldAnimeFilterBeVisible: Boolean = false,
    selectedAnimeFilter: AnimeFilter? = null,
    onAnimeFilterSelected: ((AnimeFilter?) -> Unit)? = null,
    onAnimeFilterToggled: (() -> Unit)? = null
) {
    val scrollState = rememberScrollState()
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing.small),
        modifier = modifier.horizontalScroll(scrollState, true)
    ) {
        if (shouldFilterTypeBeVisible) {
            Column {
                FilterChip(
                    selected = selectedFilterType != null,
                    onClick = {
                        onFilterTypeToggled.invoke()
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = com.lelestacia.hayate.common.shared.R.string.anime_type_with_value,
                                selectedFilterType?.name
                                    ?: stringResource(id = com.lelestacia.hayate.common.shared.R.string.all)
                            )
                        )
                    },
                    trailingIcon = {
                        AnimatedContent(
                            targetState = isFilterTypeOpened,
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
                    expanded = isFilterTypeOpened,
                    onDismissRequest = {
                        onFilterTypeToggled.invoke()
                    },
                ) {
                    HayateCustomDropDownItem(
                        text = stringResource(id = com.lelestacia.hayate.common.shared.R.string.all),
                        onClick = {
                            onFilterTypeSelected(null)
                        }
                    )
                    AnimeType.entries.forEach { selectedFilter ->
                        HayateCustomDropDownItem(
                            text = selectedFilter.name,
                            onClick = {
                                onFilterTypeSelected(selectedFilter)
                            }
                        )
                    }
                }
            }
        }

        if (shouldAnimeFilterBeVisible) {
            Column {
                FilterChip(
                    selected = selectedAnimeFilter != null,
                    onClick = {
                        onAnimeFilterToggled?.invoke()
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = com.lelestacia.hayate.common.shared.R.string.anime_filter_with_value,
                                selectedAnimeFilter?.title
                                    ?: stringResource(id = com.lelestacia.hayate.common.shared.R.string.all)
                            )
                        )
                    },
                    trailingIcon = {
                        AnimatedContent(
                            targetState = isAnimeFilterOpened,
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
                    expanded = isAnimeFilterOpened,
                    onDismissRequest = {
                        onAnimeFilterToggled?.invoke()
                    },
                ) {
                    HayateCustomDropDownItem(
                        text = stringResource(id = com.lelestacia.hayate.common.shared.R.string.all),
                        onClick = {
                            onFilterTypeSelected(null)
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
                isFilterTypeOpened = isFilterTypeOpened,
                shouldFilterTypeBeVisible = true,
                selectedFilterType = selectedFilterType,
                onFilterTypeSelected = {
                    selectedFilterType = it
                },
                onFilterTypeToggled = {
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