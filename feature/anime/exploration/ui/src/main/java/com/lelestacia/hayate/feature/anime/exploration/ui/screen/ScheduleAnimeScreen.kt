package com.lelestacia.hayate.feature.anime.exploration.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.LazyPagingItems
import com.lelestacia.hayate.common.theme.padding
import com.lelestacia.hayate.common.theme.quickSandFamily
import com.lelestacia.hayate.feature.anime.core.common.component.AnimePagingLazyGrid
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.domain.presenter.schedule.ScheduleAnimeState
import com.lelestacia.hayate.feature.anime.exploration.ui.R

@Composable
internal fun ScheduleAnimeScreen(
    scheduledAnimePaging: LazyPagingItems<Anime>,
    state: ScheduleAnimeState,
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

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.small, vertical = padding.medium)
        ) {
            val listOfTitles = stringArrayResource(id = R.array.scheduled_anime_title)
            val title by rememberSaveable {
                mutableStateOf(listOfTitles.random())
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = quickSandFamily,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }

        AnimePagingLazyGrid(
            state = state.gridState,
            animePaging = scheduledAnimePaging,
            onClick = onAnimeClicked,
            modifier = Modifier.weight(1f)
        )
    }
}