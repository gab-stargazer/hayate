package com.lelestacia.hayate.feature.anime.initialization.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.hayate.core.common.state.DataState
import com.lelestacia.hayate.feature.anime.initialization.domain.usecases.InitializationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
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

//    private val _state: MutableStateFlow<InitializationScreenState> =
//        MutableStateFlow(InitializationScreenState())
//    val state: StateFlow<InitializationScreenState> =
//        _state.asStateFlow()

    private val _isConfigFinished: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isConfigFinished: StateFlow<Boolean> = _isConfigFinished.asStateFlow()

    fun checkForFirebaseConfig() = viewModelScope.launch {
        useCases.checkForFirebaseConfig().collectLatest { dataState ->
            when (dataState) {
                DataState.Loading -> Unit
                DataState.None -> Unit
                else -> _isConfigFinished.update { true }
            }
        }
    }
}