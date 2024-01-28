package com.lelestacia.hayate.feature.anime.detail.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lelestacia.hayate.core.theme.padding
import com.lelestacia.hayate.core.theme.quickSandFamily
import com.lelestacia.hayate.feature.anime.detail.ui.R

@Composable
fun AnimeHeader(
    malID: Int,
    coverImages: String,
    title: String,
    titleJapanese: String?,
    rank: Int,
    score: Double?,
    scoredBy: Int?,
    status: String,
    isDarkTheme: Boolean,
) {

    //  TODO: Fix Coloring

    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.background.copy(
                alpha = 0.75F
            ),
            MaterialTheme.colorScheme.background
        )
    )

    val textColor = when (isDarkTheme) {
        true -> Color.White
        false -> Color.Black
    }

    //  TODO: Improve this later

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data = coverImages)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .memoryCacheKey(malID.toString())
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .diskCacheKey(malID.toString())
                    .allowHardware(false)
                    .allowRgb565(true)
                    .build(),
                contentDescription = stringResource(
                    id = R.string.cover_image,
                    title
                ),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.colorMatrix(
                    colorMatrix = ColorMatrix().apply {
                        setToSaturation(sat = 0.5F)
                    }
                ),
                filterQuality = FilterQuality.Medium,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2F)
                    .clip(RoundedCornerShape(4.dp))
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(4.dp))
                    .background(gradient)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = padding.large)
                .padding(top = padding.small)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data = coverImages)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .memoryCacheKey(malID.toString())
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .diskCacheKey(malID.toString())
                    .allowHardware(false)
                    .allowRgb565(true)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "Cover Image from Anime $title",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .widthIn(min = 100.dp, max = 150.dp)
                    .aspectRatio(3F / 4F)
                    .clip(RoundedCornerShape(4.dp))
                    .weight(1f)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 8.dp)
            ) {
                if (status != stringResource(id = R.string.not_yet_aired)) {
                    AnimeRank(
                        rank = rank.toString(),
                        isDarkTheme = isDarkTheme
                    )
                    AnimeScore(
                        score = (score ?: 0).toString(),
                        scoredBy = (scoredBy ?: 0).toString(),
                        isDarkTheme = isDarkTheme
                    )
                }

                AnimeTitle(
                    title = title,
                    titleJapanese = titleJapanese,
                    isDarkTheme = isDarkTheme
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector =
                        when (status) {
                            stringResource(id = R.string.currently_airing) -> Icons.Filled.Schedule
                            stringResource(id = R.string.finished_airing) -> Icons.Filled.Check
                            else -> Icons.Filled.Close
                        },
                        contentDescription = null,
                        tint = textColor
                    )
                    Text(
                        text = status,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontFamily = quickSandFamily,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                    )
                }
            }
        }
    }
}