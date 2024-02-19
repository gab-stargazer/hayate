package com.lelestacia.hayate.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.lelestacia.hayate.R
import com.lelestacia.hayate.core.common.Screen
import com.lelestacia.hayate.core.common.api.LoggerApi
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.event.HayateNavigationType
import com.lelestacia.hayate.core.common.state.HayateState
import com.lelestacia.hayate.core.common.util.Msg
import com.lelestacia.hayate.core.common.util.Route
import com.lelestacia.hayate.core.common.util.Source
import com.lelestacia.hayate.core.common.util.Title
import com.lelestacia.hayate.core.common.util.UiText
import com.lelestacia.hayate.domain.state.AppBarState
import com.lelestacia.hayate.domain.state.BottomNavigationState
import com.lelestacia.hayate.navigation.isRootDestination
import com.lelestacia.hayate.util.shouldAppBarBeVisible
import com.lelestacia.hayate.util.shouldSearchIconBeVisible
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HayateViewModel @Inject constructor(
    private val loggerApi: LoggerApi,
) : ViewModel() {

    private val source = Source(name = "VIEWMODEL")

    private val _applicationState: MutableStateFlow<HayateState> =
        MutableStateFlow(HayateState())
    val applicationState: StateFlow<HayateState> =
        _applicationState.asStateFlow()

    private val _navigationChannel: Channel<HayateNavigationType> = Channel()
    val navigationChannel: ReceiveChannel<HayateNavigationType> = _navigationChannel

    private val _snackBarChannel: Channel<UiText> = Channel()
    val snackBarChannel: ReceiveChannel<UiText> = _snackBarChannel

    private val _appBarState: MutableStateFlow<AppBarState> =
        MutableStateFlow(AppBarState())
    val appBarState: StateFlow<AppBarState> =
        _appBarState.asStateFlow()

    private val _bottomNavigationState: MutableStateFlow<BottomNavigationState> =
        MutableStateFlow(BottomNavigationState())
    val bottomNavigationState: StateFlow<BottomNavigationState> =
        _bottomNavigationState.asStateFlow()

    fun onEvent(event: HayateEvent) = viewModelScope.launch {
        when (event) {
            is HayateEvent.DarkThemeChanged -> {
                _appBarState.update {
                    it.copy(
                        isDarkTheme = event.isDarkTheme
                    )
                }

                _bottomNavigationState.update {
                    it.copy(
                        isDarkTheme = event.isDarkTheme
                    )
                }

                _applicationState.update { state ->
                    state.copy(
                        isDarkTheme = event.isDarkTheme
                    )
                }
            }

            is HayateEvent.DestinationChanged -> {
                _appBarState.update { currentState ->
                    currentState.copy(
                        shouldNavigationIconBeVisible = !isRootDestination(event.route),
                        shouldAppBarBeVisible = shouldAppBarBeVisible(event.route),
                        shouldSearchIconBeVisible = shouldSearchIconBeVisible(event.route)
                    )
                }

                _bottomNavigationState.update { currentState ->
                    currentState.copy(
                        selectedRoute = event.route,
                        isRootDestination = isRootDestination(event.route)
                    )
                }

            }

            is HayateEvent.OnDetailAnimeToolbar -> {
                _appBarState.update {
                    it.copy(
                        animeID = event.animeID,
                        trailerURL = event.trailerURL
                    )
                }
            }

            is HayateEvent.ShowSnackBar -> _snackBarChannel.trySend(event.message)

            is HayateEvent.Navigate -> {

                val navigationLog = when (event.navigation) {
                    is HayateNavigationType.Navigate -> "Navigating into ${(event.navigation as HayateNavigationType.Navigate).navTitle.value}"
                    is HayateNavigationType.NavigateWithTitle -> "Navigating into ${(event.navigation as HayateNavigationType.NavigateWithTitle).navTitle.value}"
                    else -> "Popping Navigation Backstack"
                }

                loggerApi.logDebug(
                    source = source,
                    msg = Msg(navigationLog)
                )

                _navigationChannel.trySend(event.navigation)

                if (
                    event.navigation is HayateNavigationType.Navigate ||
                    event.navigation is HayateNavigationType.NavigateWithTitle
                ) {

                    if (event.navigation is HayateNavigationType.Navigate) {
                        _appBarState.update { currentState ->
                            currentState.copy(
                                currentRoute = (event.navigation as HayateNavigationType.Navigate).route.value
                            )
                        }
                    } else {
                        _appBarState.update { currentState ->
                            currentState.copy(
                                currentRoute = (event.navigation as HayateNavigationType.NavigateWithTitle).route.value,
                                appBarTitle = (event.navigation as HayateNavigationType.NavigateWithTitle).title
                            )
                        }
                    }
                }

                val localAppBarState: AppBarState = appBarState.value
                if (
                    event.navigation is HayateNavigationType.PopBackstack &&
                    (localAppBarState.animeID != null || localAppBarState.trailerURL != null)
                ) {
                    _appBarState.update { currentState ->
                        currentState.copy(
                            animeID = null,
                            trailerURL = null
                        )
                    }
                }

                if (event.navigation is HayateNavigationType.PopBackstackFromTitle) {
                    _appBarState.update { currentState ->
                        currentState.copy(
                            appBarTitle = R.string.japanese_app_name
                        )
                    }
                }
            }

            HayateEvent.SearchModeToggle -> {
                _appBarState.update { currentState ->
                    currentState.copy(
                        isSearchModeActive = !currentState.isSearchModeActive
                    )
                }
            }

            is HayateEvent.SearchQueryChanged -> {
                _appBarState.update { currentState ->
                    currentState.copy(
                        searchQuery = event.searchQuery
                    )
                }
            }

            HayateEvent.SearchClicked -> {
                if (appBarState.value.currentRoute != Screen.Search.route) {
                    _navigationChannel.trySend(
                        HayateNavigationType.Navigate(
                            route = Route(Screen.Search.route),
                            options = navOptions {
                                launchSingleTop = true
                            },
                            navTitle = Title(Screen.Search::class.java.simpleName)
                        )
                    )
                }

                _applicationState.update { state ->
                    state.copy(
                        searchQuery = appBarState.value.searchQuery
                    )
                }
            }
        }
    }
}

