package com.lelestacia.hayate.common.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

open class BaseViewModel : ViewModel() {

    private val _errorMessage: Channel<String> = Channel()
    val errorMessage: Flow<String> = _errorMessage.receiveAsFlow()

    private val _route: Channel<String> = Channel()
    val route: Flow<String> = _route.receiveAsFlow()
}