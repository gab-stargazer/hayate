package com.lelestacia.hayate.common.shared

import androidx.lifecycle.ViewModel
import com.lelestacia.hayate.common.shared.util.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

open class BaseViewModel : ViewModel() {

    protected val _eventMessage: Channel<UiText> = Channel()
    val eventMessage: Flow<UiText> = _eventMessage.receiveAsFlow()

    protected val _errorMessage: Channel<UiText> = Channel()
    val errorMessage: Flow<UiText> = _errorMessage.receiveAsFlow()
}