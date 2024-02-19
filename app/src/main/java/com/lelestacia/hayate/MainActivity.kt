package com.lelestacia.hayate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lelestacia.hayate.core.common.api.FeatureApi
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.theme.AppTheme
import com.lelestacia.hayate.domain.viewmodel.HayateViewModel
import com.lelestacia.hayate.util.HandleNavigation
import com.lelestacia.hayate.util.HandleSnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationProvider: Set<@JvmSuppressWildcards FeatureApi>

    private val viewModel by viewModels<HayateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme: Boolean = isSystemInDarkTheme()
            LaunchedEffect(key1 = isDarkTheme) {
                viewModel.onEvent(HayateEvent.DarkThemeChanged(isDarkTheme))
            }

            AppTheme(
                darkTheme = isDarkTheme,
                dynamicColor = false
            ) {
                val navController = rememberNavController()
                val applicationState by viewModel.applicationState.collectAsStateWithLifecycle()
                val appBarState by viewModel.appBarState.collectAsStateWithLifecycle()
                val bottomNavigationState by viewModel.bottomNavigationState.collectAsStateWithLifecycle()

                val navigationChannel = viewModel.navigationChannel
                val snackBarChannel = viewModel.snackBarChannel

                val snackBarHostState: SnackbarHostState by remember {
                    mutableStateOf(SnackbarHostState())
                }

                val backStackEntry: NavBackStackEntry? by navController
                    .currentBackStackEntryFlow
                    .collectAsStateWithLifecycle(null)

                val uiController: SystemUiController = rememberSystemUiController()

                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Hayate(
                        featureProvider = navigationProvider,
                        navController = navController,
                        applicationState = applicationState,
                        onApplicationEvent = viewModel::onEvent,
                        appBarState = appBarState,
                        bottomNavigationState = bottomNavigationState,
                        snackBarHostState = snackBarHostState,
                        uiController = uiController
                    )
                }

                HandleNavigation(
                    navController = navController,
                    navigationChannel = navigationChannel
                )

                HandleSnackBar(
                    snackBarHost = snackBarHostState,
                    snackBarChannel = snackBarChannel
                )

                LaunchedEffect(key1 = backStackEntry) {
                    backStackEntry?.destination?.route?.let { currentRoute ->
                        viewModel.onEvent(HayateEvent.DestinationChanged(currentRoute))
                    }
                }
            }
        }
    }
}