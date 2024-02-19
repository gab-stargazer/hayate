package com.lelestacia.hayate.feature.settings.ui.di

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Left
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Right
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lelestacia.hayate.core.common.Screen
import com.lelestacia.hayate.core.common.api.FeatureApi
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.event.HayateNavigationType
import com.lelestacia.hayate.core.common.state.HayateState
import com.lelestacia.hayate.core.common.util.Route
import com.lelestacia.hayate.core.common.util.Title
import com.lelestacia.hayate.feature.settings.ui.R
import com.lelestacia.hayate.feature.settings.ui.component.MoreCustomButton
import com.lelestacia.hayate.feature.settings.ui.screen.AppInfoScreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.File
import javax.inject.Inject

internal class MoreFeatureImplementation @Inject constructor() : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        state: HayateState,
        onEvent: (HayateEvent) -> Unit,
    ) {
        navGraphBuilder.navigation(
            startDestination = Screen.MoreNavigation.More.route,
            route = Screen.MoreNavigation.route,
        ) {
            composable(
                route = Screen.MoreNavigation.More.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.Exploration.route -> slideIntoContainer(Right)
                        Screen.Collection.route -> slideIntoContainer(Left)
                        else -> null
                    }
                },
                exitTransition = {
                    fadeOut()
                },
                popEnterTransition = {
                    fadeIn()
                }
            ) {
                val context = LocalContext.current
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize()
                ) {
                    MoreCustomButton(
                        label = stringResource(R.string.application_information),
                        icon = Icons.Default.Info,
                        onClick = {
                            onEvent(
                                HayateEvent.Navigate(
                                    HayateNavigationType.NavigateWithTitle(
                                        title = R.string.application_information,
                                        route = Route(Screen.MoreNavigation.AppInfo.route),
                                        options = null,
                                        navTitle = Title(Screen.MoreNavigation.AppInfo::class.simpleName.orEmpty())
                                    )
                                )
                            )
                        },
                        isDarkTheme = state.isDarkTheme,
                        modifier = Modifier
                    )
                    MoreCustomButton(
                        label = "Privacy and Policy",
                        icon = FontAwesomeIcons.Regular.File,
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/gab-stargazer/hayate/blob/dev/docs/privacy_policy.md")
                            )
                            context.startActivity(intent)
                        },
                        isDarkTheme = state.isDarkTheme,
                        modifier = Modifier
                    )
                    MoreCustomButton(
                        label = "Terms of Service",
                        icon = FontAwesomeIcons.Regular.File,
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/gab-stargazer/hayate/blob/dev/docs/Terms_of_service.md")
                            )
                            context.startActivity(intent)
                        },
                        isDarkTheme = state.isDarkTheme,
                        modifier = Modifier
                    )
                }
            }

            composable(
                route = Screen.MoreNavigation.AppInfo.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.MoreNavigation.More.route -> fadeIn()
                        else -> null
                    }
                },
                popExitTransition = {
                    when (targetState.destination.route) {
                        Screen.MoreNavigation.More.route -> fadeOut()
                        else -> null
                    }
                }
            ) {
                AppInfoScreen(
                    isDarkTheme = state.isDarkTheme,
                    onEvent = onEvent,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}