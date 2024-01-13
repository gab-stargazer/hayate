package com.lelestacia.hayate.domain.viewmodel

import androidx.lifecycle.viewModelScope
import com.lelestacia.hayate.common.shared.BaseViewModel
import com.lelestacia.hayate.common.shared.event.HayateEvent
import com.lelestacia.hayate.component.shouldAppBarBeVisible
import com.lelestacia.hayate.domain.state.AppBarState
import com.lelestacia.hayate.domain.state.BottomNavigationState
import com.lelestacia.hayate.navigation.isRootDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HayateViewModel @Inject constructor() : BaseViewModel() {

    private val _route: Channel<String> = Channel()
    val route: Flow<String> = _route.receiveAsFlow()

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
                _appBarState.update {
                    it.copy(
                        shouldNavigationIconBeVisible = !isRootDestination(event.route),
                        shouldAppBarBeVisible = shouldAppBarBeVisible(event.route)
                    )
                }

                _bottomNavigationState.update {
                    it.copy(
                        selectedRoute = event.route,
                        isRootDestination = isRootDestination(event.route)
                    )
                }

                _route.send(event.route)
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
        }
    }
}