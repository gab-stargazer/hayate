package com.lelestacia.hayate.domain.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.lelestacia.hayate.common.shared.BaseViewModel
import com.lelestacia.hayate.common.shared.Screen
import com.lelestacia.hayate.common.shared.event.HayateEvent
import com.lelestacia.hayate.common.shared.event.HayateNavigationType
import com.lelestacia.hayate.common.shared.util.UiText
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
 * ViewModel for the Hayate application, managing various UI states and navigation events.
 *
 * @constructor Injects the dependencies using Hilt and extends [BaseViewModel].
 *
 * @property _navigationRoute MutableStateFlow to track the current navigation route.
 * @property navigationRoute Immutable StateFlow providing read-only access to the current navigation route.
 * @property _appBarState MutableStateFlow to manage the state of the application's app bar.
 * @property appBarState Immutable StateFlow providing read-only access to the app bar state.
 * @property _bottomNavigationState MutableStateFlow to manage the state of the bottom navigation.
 * @property bottomNavigationState Immutable StateFlow providing read-only access to the bottom navigation state.
 * @property _snackBarMessage MutableStateFlow to manage the state of the displayed snack bar message.
 * @property snackBarMessage Immutable StateFlow providing read-only access to the snack bar message state.
 */
@HiltViewModel
class HayateViewModel @Inject constructor() : BaseViewModel() {

    private val _navigationRoute: MutableStateFlow<HayateNavigationType?> = MutableStateFlow(null)
    val navigationRoute = _navigationRoute.asStateFlow()

    private val _appBarState: MutableStateFlow<AppBarState> =
        MutableStateFlow(AppBarState())
    val appBarState: StateFlow<AppBarState> =
        _appBarState.asStateFlow()

    private val _bottomNavigationState: MutableStateFlow<BottomNavigationState> =
        MutableStateFlow(BottomNavigationState())
    val bottomNavigationState: StateFlow<BottomNavigationState> =
        _bottomNavigationState.asStateFlow()

    private val _snackBarMessage: MutableStateFlow<UiText?> =
        MutableStateFlow(null)
    val snackBarMessage: StateFlow<UiText?> =
        _snackBarMessage.asStateFlow()

    /**
     * Handles various events triggered within the application, updating relevant UI states.
     *
     * @param event The event to be handled.
     */
    fun onEvent(event: HayateEvent) = viewModelScope.launch {
        when (event) {
            is HayateEvent.OnDarkThemeChanged -> {
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
            }

            is HayateEvent.OnDestinationChanged -> {
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

            is HayateEvent.OnDestinationChangedWithTitle -> {

            }

            is HayateEvent.OnDetailAnimeToolbar -> {
                _appBarState.update {
                    it.copy(
                        animeID = event.animeID,
                        trailerURL = event.trailerURL
                    )
                }
            }

            is HayateEvent.ShowSnackBar -> _snackBarMessage.update { event.message }

            is HayateEvent.OnNavigate -> {
                _navigationRoute.update { event.navigation }

                if (event.navigation is HayateNavigationType.Navigate) {
                    _appBarState.update { currentState ->
                        currentState.copy(
                            currentRoute = (event.navigation as HayateNavigationType.Navigate).route
                        )
                    }
                }

                if (event.navigation is HayateNavigationType.PopBackstack) {
                    _appBarState.update { currentState ->
                        currentState.copy(
                            animeID = null,
                            trailerURL = null
                        )
                    }
                }
            }

            HayateEvent.OnSearchModeToggle -> {
                _appBarState.update { currentState ->
                    currentState.copy(
                        isSearchModeActive = !currentState.isSearchModeActive
                    )
                }
            }

            is HayateEvent.OnSearchQueryChanged -> {
                _appBarState.update { currentState ->
                    currentState.copy(
                        searchQuery = event.searchQuery
                    )
                }
            }

            HayateEvent.OnSearchClicked -> {
                _navigationRoute.update {
                    HayateNavigationType.Navigate(
                        route = Screen.Search.createRoute(appBarState.value.searchQuery),
                        options = navOptions {
                            launchSingleTop = true
                        }
                    )
                }
            }
        }
    }

    /**
     * Handles the completion of displaying the snack bar message by resetting its state to null.
     */
    fun onSnackBarMessageHandled() = viewModelScope.launch {
        _snackBarMessage.update { null }
    }

    /**
     * Clears the current navigation route, triggering any observers to update accordingly.
     * This function is intended to be called when navigation actions are completed.
     */
    fun handleNavigation() = viewModelScope.launch {
        _navigationRoute.update { null }
    }
}

