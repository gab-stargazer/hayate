package com.lelestacia.hayate.component

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lelestacia.hayate.common.shared.event.HayateEvent
import com.lelestacia.hayate.common.theme.spacing
import com.lelestacia.hayate.domain.state.AppBarState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Youtube

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    state: AppBarState,
    onEvent: (HayateEvent) -> Unit,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val textColor = when (state.isDarkTheme) {
        true -> Color.White
        false -> MaterialTheme.colorScheme.primary
    }
    AnimatedVisibility(
        visible = state.shouldAppBarBeVisible,
        enter = slideInVertically(
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            ),
            initialOffsetY = { fullHeight ->
                fullHeight / 2
            }
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        ), exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            ),
            targetOffsetY = { fullHeight ->
                fullHeight / 2
            }
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = state.appBarTitle),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = textColor
                    )
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            navigationIcon = {
                AnimatedVisibility(
                    visible = state.shouldNavigationIconBeVisible,
                    enter = slideInHorizontally(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    ),
                    exit = slideOutHorizontally(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    )
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                            if (state.animeID != null || state.trailerURL != null) {
                                onEvent(
                                    HayateEvent.OnDetailAnimeToolbar(
                                        animeID = null,
                                        trailerURL = null
                                    )
                                )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = textColor
                        )
                    }
                }
            },
            actions = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.small),
                    modifier = Modifier.animateContentSize()
                ) {

                    //  Button Share
                    AnimatedVisibility(
                        visible = state.animeID != null,
                        enter = slideInHorizontally(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            ),
                            initialOffsetX = { fullWidth -> fullWidth }
                        ) + fadeIn(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            )
                        ),
                        exit = slideOutHorizontally(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            ),
                            targetOffsetX = { fullWidth -> fullWidth }
                        ) + fadeOut(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            )
                        )
                    ) {
                        IconButton(
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://myanimelist.net/anime/${state.animeID}/")
                                )
                                context.startActivity(intent)
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Share Anime"
                                )
                            }
                        )
                    }

                    //  Button Trailer
                    AnimatedVisibility(
                        visible = state.trailerURL != null,
                        enter = slideInHorizontally(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            ),
                            initialOffsetX = { fullWidth -> fullWidth }
                        ) + fadeIn(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            )
                        ),
                        exit = slideOutHorizontally(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            ),
                            targetOffsetX = { fullWidth -> fullWidth }
                        ) + fadeOut(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            )
                        )
                    ) {
                        IconButton(
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(state.trailerURL)
                                )
                                context.startActivity(intent)
                            },
                            content = {
                                Icon(
                                    imageVector = FontAwesomeIcons.Brands.Youtube,
                                    contentDescription = "Watch Anime Trailer",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        )
                    }
                }
            }
        )
    }
}