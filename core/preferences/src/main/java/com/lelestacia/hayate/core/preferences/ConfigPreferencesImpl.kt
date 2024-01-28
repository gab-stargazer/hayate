package com.lelestacia.hayate.core.preferences

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.lelestacia.hayate.core.preferences.Constant.IS_INITIALIZED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ConfigPreferencesImpl @Inject constructor(
    private val context: Context,
) : ConfigPreferences {

    private val isAnimeInitializedKey = booleanPreferencesKey(IS_INITIALIZED)

    override fun isAnimeFeatureInitialized(): Flow<Boolean> =
        context.dataStore.data.map { config: Preferences ->
            config[isAnimeInitializedKey] ?: false
        }

    override suspend fun featureAnimeInitiated() {
        context.dataStore.edit { config: MutablePreferences ->
            config[isAnimeInitializedKey] = true
        }
    }

    override suspend fun updateFeature(value: Map<String, Boolean>) {
        context.dataStore.edit { config: MutablePreferences ->
            value.keys.forEach { key ->
                val prefKey = booleanPreferencesKey(key)
                config[prefKey] = value[key] ?: true
            }
        }
    }

    override fun isFeatureEnabled(key: String): Flow<Boolean> =
        context.dataStore.data.map { config: Preferences ->
            val prefKey = booleanPreferencesKey(key)
            config[prefKey] ?: true
        }
}