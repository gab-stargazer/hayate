package com.lelestacia.hayate.feature.anime.detail.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.lelestacia.hayate.common.shared.BaseViewModel
import com.lelestacia.hayate.common.shared.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {

    fun sendMessage(message: UiText) = viewModelScope.launch {
        _eventMessage.send(message)
    }
}