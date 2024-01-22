package com.lelestacia.hayate.feature.anime.search.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.LazyPagingItems
import com.lelestacia.hayate.common.theme.padding
import com.lelestacia.hayate.feature.anime.core.common.component.HayateAnimeDropDownFilter
import com.lelestacia.hayate.feature.anime.core.common.component.SearchAnimePaging
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.search.ui.presenter.SearchEvent
import com.lelestacia.hayate.feature.anime.search.ui.presenter.SearchState

@Composable
fun SearchScreen(
    query: String,
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
    anime: LazyPagingItems<Anime>,
    onClicked: (Anime) -> Unit,
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
            isAnimeTypeOpened = state.isAnimeTypeMenuOpened,
            shouldAnimeTypeBeVisible = true,
            selectedAnimeType = state.animeType,
            onAnimeTypeSelected = { newAnimeType ->
                onEvent(SearchEvent.OnAnimeTypeChanged(newAnimeType))
            },
            onAnimeTypeToggled = {
                onEvent(SearchEvent.OnAnimeTypeMenuToggled)
            },
            isAnimeRatingOpened = state.isAnimeRatingMenuOpened,
            shouldAnimeRatingBeVisible = true,
            selectedAnimeRating = state.animeRating,
            onAnimeRatingSelected = { newAnimeRating ->
                onEvent(SearchEvent.OnAnimeRatingChanged(newAnimeRating))
            },
            onAnimeRatingToggled = {
                onEvent(SearchEvent.OnAnimeRatingMenuToggled)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.medium)
        )
        SearchAnimePaging(
            query = query,
            state = rememberLazyGridState(),
            animePaging = anime,
            onClick = onClicked,
            modifier = Modifier.weight(1f)
        )
    }
}