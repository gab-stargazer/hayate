package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.hayate.core.common.state.UiState
import com.lelestacia.hayate.core.common.util.UiText
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

internal open class BaseExploreViewModel(
    private val animeUseCases: AnimeUseCases,
) : ViewModel() {

    fun insertAnime(anime: Anime) = viewModelScope.launch {
        Timber.d("Insert Anime VM")
        animeUseCases.insertAnime(anime).collectLatest { state ->
            when (state) {
                is UiState.Failed -> Timber.e("Inserting Anime Failed. Reason: ${(state.error as UiText.MessageString).message}")
                UiState.Loading -> Timber.d("Inserting Anime into DB")
                UiState.None -> Unit
                is UiState.Success -> Timber.d("Anime insertion completed")
            }
        }
    }
}