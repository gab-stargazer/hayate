package com.lelestacia.hayate.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.lelestacia.hayate.R
import com.lelestacia.hayate.core.common.Screen
import com.lelestacia.hayate.core.common.event.HayateEvent
import com.lelestacia.hayate.core.common.event.HayateNavigationType
import com.lelestacia.hayate.core.common.state.HayateState
import com.lelestacia.hayate.core.common.state.UiState
import com.lelestacia.hayate.core.common.util.UiText
import com.lelestacia.hayate.domain.state.AppBarState
import com.lelestacia.hayate.domain.state.BottomNavigationState
import com.lelestacia.hayate.navigation.isRootDestination
import com.lelestacia.hayate.util.shouldAppBarBeVisible
import com.lelestacia.hayate.util.shouldSearchIconBeVisible
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing the state and logic of the Hayate application.
 *
 * This ViewModel handles various UI states such as navigation, Snackbar display, AppBar configuration,
 * and Bottom Navigation state. It processes events triggered by the UI and updates the corresponding
 * state flows accordingly.
 *
 * @property _navigationState MutableStateFlow representing the navigation state.
 * @property navigationState Immutable StateFlow exposing the navigation state to observers.
 * @property _snackBarState MutableStateFlow representing the Snackbar state.
 * @property snackBarState Immutable StateFlow exposing the Snackbar state to observers.
 * @property _appBarState MutableStateFlow representing the AppBar state.
 * @property appBarState Immutable StateFlow exposing the AppBar state to observers.
 * @property _bottomNavigationState MutableStateFlow representing the Bottom Navigation state.
 * @property bottomNavigationState Immutable StateFlow exposing the Bottom Navigation state to observers.
 *
 * @see UiState
 * @see AppBarState
 * @see BottomNavigationState
 * @see UiText
 * @see HayateEvent
 * @see HayateNavigationType
 * @author Kamil Malik
 * @since 23 January 2024
 */
@HiltViewModel
class HayateViewModel @Inject constructor() : ViewModel() {

    private val _applicationState: MutableStateFlow<HayateState> =
        MutableStateFlow(HayateState())
    val applicationState: StateFlow<HayateState> =
        _applicationState.asStateFlow()

    private val _navigationState: MutableStateFlow<UiState<HayateNavigationType>> =
        MutableStateFlow(UiState.Handled)
    val navigationState: StateFlow<UiState<HayateNavigationType>> =
        _navigationState.asStateFlow()

    private val _snackBarState: MutableStateFlow<UiState<UiText>> =
        MutableStateFlow(UiState.Handled)
    val snackBarState: StateFlow<UiState<UiText>> =
        _snackBarState.asStateFlow()

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

            is HayateEvent.ShowSnackBar -> _snackBarState.update {
                UiState.NotHandled(
                    data = event.message
                )
            }

            is HayateEvent.Navigate -> {
                _navigationState.update {
                    UiState.NotHandled(
                        data = event.navigation
                    )
                }

                if (
                    event.navigation is HayateNavigationType.Navigate ||
                    event.navigation is HayateNavigationType.NavigateWithTitle
                ) {

                    if (event.navigation is HayateNavigationType.Navigate) {
                        _appBarState.update { currentState ->
                            currentState.copy(
                                currentRoute = (event.navigation as HayateNavigationType.Navigate).route
                            )
                        }
                    } else {
                        _appBarState.update { currentState ->
                            currentState.copy(
                                currentRoute = (event.navigation as HayateNavigationType.NavigateWithTitle).route,
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
                    _navigationState.update {
                        UiState.NotHandled(
                            data = HayateNavigationType.Navigate(
                                route = Screen.Search.route,
                                options = navOptions {
                                    launchSingleTop = true
                                }
                            )
                        )
                    }
                }

                _applicationState.update { state ->
                    state.copy(
                        searchQuery = appBarState.value.searchQuery
                    )
                }
            }
        }
    }


    /**
     * Handles the Snackbar state by updating it to [UiState.Handled].
     */
    fun handleSnackBar() = viewModelScope.launch {
        _snackBarState.update { UiState.Handled }
    }

    /**
     * Handles the navigation state by updating it to [UiState.Handled].
     */
    fun handleNavigation() = viewModelScope.launch {
        _navigationState.update { UiState.Handled }
    }
}

