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
import com.lelestacia.hayate.feature.anime.exploration.ui.presenter.airing.AiringAnimeEvent
import com.lelestacia.hayate.feature.anime.exploration.ui.presenter.airing.AiringAnimeState

@Composable
internal fun AiringAnimeScreen(
    airingAnimePaging: LazyPagingItems<Anime>,
    state: AiringAnimeState,
    onEvent: (AiringAnimeEvent) -> Unit,
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
            isAnimeTypeOpened = state.isTypeMenuOpened,
            selectedAnimeType = state.animeType,
            onAnimeTypeSelected = { selectedFilter ->
                onEvent(AiringAnimeEvent.OnAnimeFilterChanged(selectedFilter))
            },
            onAnimeTypeToggled = {
                onEvent(AiringAnimeEvent.OnTypeFilterMenuToggled)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.small, vertical = padding.extraSmall)
        )

        AnimePagingLazyGrid(
            state = state.gridState,
            animePaging = airingAnimePaging,
            onClick = onAnimeClicked,
            modifier = Modifier.weight(1f)
        )
    }
}