package com.lelestacia.hayate.domain.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HayateViewModel @Inject constructor() : BaseViewModel() {

    /**
     *   New Navigation system, the route will be private to viewModel, we only launch
     *   a scope from UI, then viewModel handle everything. We still have disposable effect
     *   for disposing the channel when orientation changes or process death happen. If you know
     *   how to handle it only when process death happen,
     *   it will help me so much since i have no idea
     */
    private val _navigationRoute: Channel<HayateNavigationType> = Channel()
    private val navigationRoute: Flow<HayateNavigationType> = _navigationRoute.consumeAsFlow()

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
                _navigationRoute.send(event.navigation)

                if (event.navigation is HayateNavigationType.Navigate) {
                    _appBarState.update { currentState ->
                        currentState.copy(
                            currentRoute = (event.navigation as HayateNavigationType.Navigate).route
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
                _navigationRoute.send(
                    HayateNavigationType.Navigate(
                        route = Screen.Search.createRoute(appBarState.value.searchQuery),
                        options = navOptions {
                            launchSingleTop = true
                        }
                    )
                )
            }
        }
    }

    fun onSnackBarMessageHandled() = viewModelScope.launch {
        _snackBarMessage.update { null }
    }

    fun setupNavController(navController: NavController) = viewModelScope.launch {
        try {
            navigationRoute.collectLatest { navType ->
                when (navType) {
                    is HayateNavigationType.Navigate -> navController.navigate(
                        route = navType.route,
                        navOptions = navType.options
                    )

                    HayateNavigationType.PopBackstack -> navController.popBackStack()
                }
            }
        } catch (e: Exception) {
            Timber.w("Navigation Route will not be recollected")
        }
    }
}

