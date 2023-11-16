package com.lelestacia.hayate.feature.anime.exploration.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lelestacia.hayate.common.theme.padding
import com.lelestacia.hayate.common.theme.spacing
import com.lelestacia.hayate.feature.anime.exploration.domain.model.Anime

@Composable
fun AnimeCard(
    anime: Anime,
    onClicked: (Anime) -> Unit,
    isDarkTheme: Boolean
) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current

    val cacheKey = anime.malId.toString()

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = spacing.extraSmall
        )
    ) {
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .clip(RoundedCornerShape(4))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.15f
                    )
                )
                .clickable {
                    onClicked(anime)
                }
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(context)
                    .data(anime.images.webp.largeImageUrl)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .diskCacheKey(cacheKey)
                    .lifecycle(lifecycle)
                    .networkCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .memoryCacheKey(cacheKey)
                    .build(),
                contentDescription = anime.title,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = com.lelestacia.hayate.common.shared.R.drawable.placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3 / 4F)
            )

            anime.rank?.let { validRank ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topEndPercent = 25
                            )
                        )
                        .background(
                            when (isDarkTheme) {
                                true -> MaterialTheme.colorScheme.surfaceVariant
                                false -> MaterialTheme.colorScheme.primary
                            }
                        )
                ) {
                    Text(
                        text = "#$validRank",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = when (isDarkTheme) {
                                true -> Color.White
                                false -> MaterialTheme.colorScheme.onPrimary
                            },
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        )
                    )
                }
            }
        }

        Text(
            text = anime.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color =
                if (isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Color.Black
                }
            ),
            modifier = Modifier.padding(
                horizontal = padding.extraSmall
            )
        )
    }
}