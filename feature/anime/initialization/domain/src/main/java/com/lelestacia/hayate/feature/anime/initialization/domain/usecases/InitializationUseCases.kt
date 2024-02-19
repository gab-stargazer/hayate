package com.lelestacia.hayate.feature.anime.initialization.domain.usecases

import com.lelestacia.hayate.core.common.state.UiState
import com.lelestacia.hayate.core.common.util.UiText
import com.lelestacia.hayate.core.firebase.FirebaseConfig
import com.lelestacia.hayate.core.preferences.ConfigPreferences
import com.lelestacia.hayate.feature.anime.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class InitializationUseCases @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val configPreferences: ConfigPreferences
) {

//    fun isAnimeFeatureInitialized(): Flow<Boolean> {
//        return configPreferences.isAnimeFeatureInitialized()
//    }

//    suspend fun featureAnimeInitiated() {
//        configPreferences.featureAnimeInitiated()
//    }

//    fun initiateFeature(): Flow<DataState<Boolean>> {
//        return animeRepository.initiateApp()
//    }

    fun checkForFirebaseConfig(): Flow<UiState<Boolean>> =
        flow<UiState<Boolean>> {
            val configs: Map<String, Boolean> = FirebaseConfig.getConfig()
            configPreferences.updateFeature(configs)
            emit(UiState.Success(true))
        }.catch { t ->
            Timber.e(t.stackTraceToString())
            emit(UiState.Failed(UiText.MessageString(t.message.orEmpty())))
        }
}