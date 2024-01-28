package com.lelestacia.hayate.feature.settings.ui.di

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Left
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Right
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
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
            startDestination = Screen.More.route,
            route = Screen.MoreNavigation.route,
        ) {
            composable(
                route = Screen.More.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.Exploration.route -> slideIntoContainer(Right)
                        Screen.Collection.route -> slideIntoContainer(Left)
                        else -> null
                    }
                },
                exitTransition = {
                    when (targetState.destination.route) {
                        Screen.Exploration.route -> slideOutOfContainer(Left)
                        Screen.Collection.route -> slideOutOfContainer(Right)
                        Screen.AppInfo.route -> fadeOut()
                        else -> null
                    }
                },
                popEnterTransition = {
                    fadeIn()
                }
            ) {
                val context = LocalContext.current
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MoreCustomButton(
                        label = stringResource(R.string.application_information),
                        icon = Icons.Default.Info,
                        onClick = {
                            onEvent(
                                HayateEvent.Navigate(
                                    HayateNavigationType.NavigateWithTitle(
                                        title = R.string.application_information,
                                        route = Screen.AppInfo.route,
                                        options = null
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
                route = Screen.AppInfo.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.More.route -> slideIntoContainer(Up) + fadeIn()
                        else -> null
                    }
                },
                popExitTransition = {
                    when (targetState.destination.route) {
                        Screen.More.route -> slideOutOfContainer(Down) + fadeOut()
                        else -> null
                    }
                }
            ) {
                AppInfoScreen(
                    isDarkTheme = state.isDarkTheme,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}