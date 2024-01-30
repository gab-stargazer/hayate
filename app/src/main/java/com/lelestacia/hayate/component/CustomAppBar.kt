package com.lelestacia.hayate.component

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.hayate.R
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.event.HayateNavigationType
import com.lelestacia.hayate.core.common.util.UiText
import com.lelestacia.hayate.core.theme.AppTheme
import com.lelestacia.hayate.core.theme.padding
import com.lelestacia.hayate.core.theme.quickSandFamily
import com.lelestacia.hayate.core.theme.spacing
import com.lelestacia.hayate.domain.state.AppBarState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Youtube

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CustomAppBar(
    state: AppBarState,
    onEvent: (HayateEvent) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val textColor by animateColorAsState(
        targetValue =
        when (state.isDarkTheme) {
            true -> Color.White
            false -> when (state.shouldNavigationIconBeVisible) {
                true -> MaterialTheme.colorScheme.onBackground
                false -> MaterialTheme.colorScheme.primary
            }
        },
        label = "Text Color Animation"
    )

    val iconColor by animateColorAsState(
        targetValue = when (state.isDarkTheme) {
            true -> Color.White.copy(
                alpha = 0.75F
            )

            false -> Color.Black.copy(
                alpha = 0.75F
            )
        },
        label = "Icon Color Animation"
    )

    var isSearchFieldFocused by remember {
        mutableStateOf(false)
    }

    BackHandler(enabled = isSearchFieldFocused) {
        focusManager.clearFocus()
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
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.small),
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    testTagsAsResourceId = true
                }
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
                                if (state.appBarTitle != R.string.japanese_app_name) {
                                    onEvent(HayateEvent.Navigate(HayateNavigationType.PopBackstackFromTitle))
                                } else {
                                    onEvent(HayateEvent.Navigate(HayateNavigationType.PopBackstack))
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = iconColor
                            )
                        }
                    }
                },
                actions = {
                    Box(
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        //  Button Search
                        AnimatedButton(
                            showCondition = state.shouldSearchIconBeVisible,
                            icon = when (state.isSearchModeActive) {
                                true -> Icons.Default.SearchOff
                                false -> Icons.Default.Search
                            },
                            onClick = {
                                onEvent(HayateEvent.SearchModeToggle)
                            },
                            color = iconColor,
                            contentDescription = "Search Button",
                            modifier = Modifier.testTag("button:search")
                        )

                        Row {

                            //  Button Share
                            AnimatedButton(
                                showCondition = state.animeID != null,
                                icon = Icons.Default.Share,
                                onClick = {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://myanimelist.net/anime/${state.animeID}/")
                                    )
                                    context.startActivity(intent)
                                },
                                color = iconColor,
                                contentDescription = "Share Anime"
                            )

                            //  Button Share
                            AnimatedButton(
                                showCondition = state.trailerURL != null,
                                icon = FontAwesomeIcons.Brands.Youtube,
                                onClick = {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(state.trailerURL)
                                    )
                                    context.startActivity(intent)
                                },
                                color = iconColor,
                                contentDescription = "Watch Anime Trailer",
                                iconSize = 24.dp
                            )
                        }
                    }
                }
            )
            AnimatedVisibility(
                visible = state.isSearchModeActive && state.shouldSearchIconBeVisible,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                TextField(
                    value = state.searchQuery,
                    onValueChange = { newSearchQuery ->
                        onEvent(HayateEvent.SearchQueryChanged(newSearchQuery))
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search_box_placeholder),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontFamily = quickSandFamily
                            )
                        )
                    },
                    prefix = {
                        if (state.searchQuery.isNotBlank()) {
                            Text(
                                text = stringResource(R.string.search_box_prefix),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = quickSandFamily
                                )
                            )
                        } else {
                            Unit
                        }
                    },
                    textStyle = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = quickSandFamily
                    ),
                    shape = RoundedCornerShape(
                        15
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = MaterialTheme.colorScheme
                            .surfaceColorAtElevation(5.dp),
                        focusedTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (state.searchQuery.isNotBlank()) {
                                //  Later this should be able to search more than once
                                onEvent(HayateEvent.SearchClicked)
                            } else {
                                onEvent(
                                    HayateEvent.ShowSnackBar(
                                        UiText.ResourceID(
                                            id = R.string.error_empty_query,
                                            args = emptyList()
                                        )
                                    )
                                )
                            }

                            focusManager.clearFocus()
                        }
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = padding.medium,
                            vertical = padding.small
                        )
                        .focusable()
                        .onFocusChanged {
                            isSearchFieldFocused = it.isFocused
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomAppBar() {
    AppTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state by remember {
                mutableStateOf(
                    AppBarState(
                        isDarkTheme = true,
                        shouldAppBarBeVisible = true,
                        shouldNavigationIconBeVisible = false,
                        isSearchModeActive = true,
                        searchQuery = "Fullmetal Alchemist",
                        shouldSearchIconBeVisible = true,
                        appBarTitle = R.string.japanese_app_name,
                        animeID = null,
                        trailerURL = null
                    )
                )
            }
            CustomAppBar(
                state = state,
                onEvent = {

                }
            )
        }
    }
}