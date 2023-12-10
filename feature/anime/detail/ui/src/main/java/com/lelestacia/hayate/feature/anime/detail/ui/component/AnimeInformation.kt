package com.lelestacia.hayate.feature.anime.detail.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lelestacia.hayate.common.shared.util.isNotEmpty
import com.lelestacia.hayate.common.shared.util.isNotNullOrEmpty
import com.lelestacia.hayate.common.theme.quickSandFamily
import com.lelestacia.hayate.common.theme.spacing
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.detail.ui.R
import com.lelestacia.hayate.feature.anime.detail.ui.util.asFormattedDate
import com.lelestacia.hayate.feature.anime.detail.ui.util.asText

@Composable
internal fun AnimeInformation(
    anime: Anime,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {

    //  TODO: Decide if the information should expand first or not
    val isExpanded by remember {
        mutableStateOf(true)
    }

    val dividerColor = when (isDarkTheme) {
        true -> MaterialTheme.colorScheme.tertiary
        false -> MaterialTheme.colorScheme.primary
    }

    Text(
        text = stringResource(R.string.information),
        style = MaterialTheme.typography.titleMedium.copy(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = quickSandFamily
        )
    )

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = spacing.small),
        ) {
            //  Type
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.type),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = quickSandFamily
                    )
                )
                Text(
                    text = anime.type.orEmpty(),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = quickSandFamily
                    )
                )
            }
            Divider(color = dividerColor)

            //  Episodes
            if (anime.episodes.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.episode),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = quickSandFamily
                        )
                    )
                    Text(
                        text = (anime.episodes as Int).toString(),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = quickSandFamily
                        )
                    )
                }
                Divider(color = dividerColor)
            }

            //  Season & Year
            if (anime.season.isNotNullOrEmpty()) {
                val season = (anime.season as String)
                    .replaceFirstChar {
                        it.uppercase()
                    }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.season),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = quickSandFamily
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.season_and_year,
                            season,
                            anime.year ?: 0
                        ),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = quickSandFamily
                        )
                    )
                }
                Divider(color = dividerColor)
            }

            //  Airing Information
            if (anime.aired.from.isNotNullOrEmpty()) {
                val airingPeriod =
                    if (anime.aired.to.isNullOrEmpty()) {
                        (anime.aired.from as String).asFormattedDate()
                    } else {
                        val startedDate = (anime.aired.from as String).asFormattedDate()
                        val finishedDate = (anime.aired.to as String).asFormattedDate()
                        stringResource(
                            id = R.string.start_to_finish_date,
                            startedDate,
                            finishedDate
                        )
                    }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.aired),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = quickSandFamily
                        )
                    )
                    Text(
                        text = airingPeriod,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = quickSandFamily
                        )
                    )
                }
                Divider(color = dividerColor)
            }

            //  Studio
            if (anime.studios.isNotEmpty()) {
                val studios = anime.studios.map { studio -> studio.name }.asText()
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.studio),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = quickSandFamily
                        )
                    )
                    Text(
                        text = studios,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = quickSandFamily
                        )
                    )
                }
                Divider(color = dividerColor)
            }

            //  Duration
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.duration),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = quickSandFamily
                    )
                )
                Text(
                    text = anime.duration,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = quickSandFamily
                    )
                )
            }
            Divider(color = dividerColor)

            //  Rating
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.rating),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = quickSandFamily
                    )
                )
                Text(
                    text = anime.rating.orEmpty(),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = quickSandFamily
                    )
                )
            }
        }
    }
}