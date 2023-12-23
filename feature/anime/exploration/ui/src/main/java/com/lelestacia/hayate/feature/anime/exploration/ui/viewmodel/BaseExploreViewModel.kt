package com.lelestacia.hayate.feature.anime.exploration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.hayate.common.shared.DataState
import com.lelestacia.hayate.common.shared.util.UiText
import com.lelestacia.hayate.feature.anime.core.domain.model.Anime
import com.lelestacia.hayate.feature.anime.exploration.domain.usecases.AnimeUseCases
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

open class BaseExploreViewModel(
    private val animeUseCases: AnimeUseCases
) : ViewModel() {

    fun insertAnime(anime: Anime) = viewModelScope.launch {
        animeUseCases.insertAnime(anime).collectLatest { state ->
            when (state) {
                is DataState.Failed -> Timber.e("Inserting Anime Failed. Reason: ${(state.error as UiText.MessageString).message}")
                DataState.Loading -> Timber.d("Inserting Anime into DB")
                DataState.None -> Unit
                is DataState.Success -> Timber.d("Anime insertion completed")
            }
        }
    }
}