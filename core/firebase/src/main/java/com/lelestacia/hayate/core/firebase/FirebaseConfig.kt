package com.lelestacia.hayate.core.firebase

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.tasks.await
import timber.log.Timber

object FirebaseConfig {

    suspend fun getConfig(): Map<String, Boolean> {
        Firebase.remoteConfig.fetchAndActivate().await()
        val configs: MutableMap<String, FirebaseRemoteConfigValue> = Firebase.remoteConfig.all
        val configBoolean: MutableMap<String, Boolean> = mutableMapOf()

        Timber.d("Hayate Feature Configuration")
        configs.keys.forEach { key ->
            val value = configs[key]?.asBoolean() ?: true
            val isEnabled: (Boolean) -> String = {
                when (it) {
                    true -> "Enabled"
                    false -> "Disabled"
                }
            }
            Timber.d("Feature $key is ${isEnabled(value)}")
            configBoolean[key] = value
        }

        return configBoolean
    }
}