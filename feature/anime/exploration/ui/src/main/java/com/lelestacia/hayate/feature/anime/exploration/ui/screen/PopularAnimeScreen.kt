package com.lelestacia.hayate.feature.anime.exploration.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.LazyPagingItems
import com.lelestacia.hayate.common.theme.padding
import com.lelestacia.hayate.feature.anime.core.common.component.AnimePagingLazyGrid
import com.lelestacia.hayate.feature.anime.core.common.component.HayateAnimeDropDownFilter
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.ui.presenter.popular.PopularAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.ui.presenter.popular.PopularAnimeState

@Composable
internal fun PopularAnimeScreen(
    popularAnimePaging: LazyPagingItems<Anime>,
    state: PopularAnimeState,
    onEvent: (PopularAnimeEvent) -> Unit,
    onAnimeClicked: (Anime) -> Unit,
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
            shouldAnimeTypeBeVisible = true,
            isAnimeTypeOpened = state.isAnimeTypeMenuOpened,
            selectedAnimeType = state.animeType,
            onAnimeTypeSelected = { selectedType ->
                onEvent(PopularAnimeEvent.OnAnimeTypeChanged(selectedType))
            },
            onAnimeTypeToggled = {
                onEvent(PopularAnimeEvent.OnAnimeTypeMenuToggled)
            },
            shouldAnimeFilterBeVisible = true,
            isAnimeFilterOpened = state.isAnimeFilterMenuOpened,
            selectedAnimeFilter = state.animeFilter,
            onAnimeFilterSelected = { selectedFilter ->
                onEvent(PopularAnimeEvent.OnAnimeFilterChanged(selectedFilter))
            },
            onAnimeFilterToggled = {
                onEvent(PopularAnimeEvent.OnAnimeFilterMenuToggled)
            },
            shouldAnimeRatingBeVisible = true,
            isAnimeRatingOpened = state.isAnimeRatingMenuOpened,
            selectedAnimeRating = state.animeRating,
            onAnimeRatingSelected = { selectedRating ->
                onEvent(PopularAnimeEvent.OnAnimeRatingChanged(selectedRating))
            },
            onAnimeRatingToggled = {
                onEvent(PopularAnimeEvent.OnAnimeRatingMenuToggled)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.small, vertical = padding.extraSmall)
        )

        AnimePagingLazyGrid(
            state = state.gridState,
            animePaging = popularAnimePaging,
            onClick = onAnimeClicked,
            modifier = Modifier.weight(1f)
        )
    }
}