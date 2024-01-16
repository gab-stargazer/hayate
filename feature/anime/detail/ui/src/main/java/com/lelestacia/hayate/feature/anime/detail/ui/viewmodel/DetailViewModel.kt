package com.lelestacia.hayate.feature.anime.detail.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.hayate.common.shared.DataState
import com.lelestacia.hayate.feature.anime.detail.domain.usecases.DetailAnimeUseCases
import com.lelestacia.hayate.feature.anime.detail.ui.presenter.DetailAnimeEvent
import com.lelestacia.hayate.feature.anime.detail.ui.presenter.DetailAnimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: DetailAnimeUseCases,
) : ViewModel() {

    private val _state: MutableStateFlow<DetailAnimeState> =
        MutableStateFlow(DetailAnimeState())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _isAnimeOnWatchList: Flow<Boolean> =
        _state.flatMapLatest {
            useCases.getWatchListByAnimeID(animeID = it.anime?.malId ?: 0)
        }

    val state: StateFlow<DetailAnimeState> = combine(
        _state,
        _isAnimeOnWatchList
    ) { screenState, isOnWatchList ->
        screenState.copy(
            isOnWatchList = isOnWatchList
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = DetailAnimeState()
    )

    fun getAnimeByAnimeID(animeID: Int) = viewModelScope.launch {
        useCases.getAnimeByAnimeID(animeID).collectLatest { result ->
            if (result is DataState.Success) {
                _state.update {
                    it.copy(
                        anime = result.data
                    )
                }
            }
        }
    }

    fun onEvent(event: DetailAnimeEvent) = viewModelScope.launch {
        when (event) {
            is DetailAnimeEvent.InsertWatchList -> insertWatchList(event.animeID)
        }
    }

    private fun insertWatchList(animeID: Int) = viewModelScope.launch {
        useCases.insertWatchList(animeID)
    }
}