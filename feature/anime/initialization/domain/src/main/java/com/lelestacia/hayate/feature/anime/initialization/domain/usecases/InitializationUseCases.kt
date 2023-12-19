package com.lelestacia.hayate.feature.anime.initialization.domain.usecases

import com.lelestacia.hayate.common.preferences.ConfigPreferences
import com.lelestacia.hayate.common.shared.DataState
import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InitializationUseCases @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val configPreferences: ConfigPreferences
) {

    fun isAnimeFeatureInitialized(): Flow<Boolean> {
        return configPreferences.isAnimeFeatureInitialized()
    }

    suspend fun featureAnimeInitiated() {
        configPreferences.featureAnimeInitiated()
    }

    fun initiateFeature(): Flow<DataState<Boolean>> {
        return animeRepository.initiateApp()
    }
}