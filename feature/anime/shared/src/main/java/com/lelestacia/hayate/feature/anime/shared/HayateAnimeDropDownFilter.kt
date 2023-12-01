package com.lelestacia.hayate.feature.anime.shared

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.lelestacia.hayate.common.shared.HayateCustomDropDownItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HayateAnimeDropDownFilter(
    modifier: Modifier = Modifier,
    isFilterTypeOpened: Boolean,
    shouldFilterTypeBeVisible: Boolean,
    selectedFilterType: AnimeTypeFilter?,
    onFilterTypeSelected: (AnimeTypeFilter?) -> Unit,
    onFilterTypeToggled: () -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = modifier
            .horizontalScroll(scrollState, true)
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
                    AnimeTypeFilter.entries.forEach { selectedFilter ->
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
    }
}