package com.lelestacia.hayate.common.preferences

import kotlinx.coroutines.flow.Flow

interface ConfigPreferences {

    fun isAnimeFeatureInitialized(): Flow<Boolean>

    suspend fun featureAnimeInitiated()

    suspend fun updateFeature(value: Map<String, Boolean>)

    fun isFeatureEnabled(key: String): Flow<Boolean>
}