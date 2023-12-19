package com.lelestacia.hayate.feature.anime.initialization.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.hayate.common.shared.DataState
import com.lelestacia.hayate.feature.anime.initialization.domain.state.InitializationScreenState
import com.lelestacia.hayate.feature.anime.initialization.domain.usecases.InitializationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class InitializationViewModel @Inject constructor(
    private val useCases: InitializationUseCases
) : ViewModel() {

    private val _state: MutableStateFlow<InitializationScreenState> =
        MutableStateFlow(InitializationScreenState())
    val state: StateFlow<InitializationScreenState> = _state.asStateFlow()

    val isInitiated: Flow<Boolean> = useCases.isAnimeFeatureInitialized()

    fun initiate() = viewModelScope.launch {
        useCases.initiateFeature().collectLatest { result ->
            _state.update { it.copy(initializationResult = result) }

            if (result is DataState.Success) {
                useCases.featureAnimeInitiated()
            }
        }
    }
}