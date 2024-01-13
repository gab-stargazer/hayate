package com.lelestacia.hayate.common.firebase

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.lelestacia.hayate.common.preferences.ConfigPreferences
import com.lelestacia.hayate.common.shared.util.IoDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HayateFirebaseRemoteConfig @Inject constructor(
    private val preferences: ConfigPreferences,
    @IoDispatcher private val ioDispatcher: CoroutineContext,
) {

    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 1800 // Check every 30 minutes
        fetchTimeoutInSeconds = 5 // Timeout after 5 seconds of waiting
    }

    fun listenToUpdate() {
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                fetchAndUpdate()
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                Timber.e(error.stackTraceToString())
            }
        })
    }

    private fun fetchAndUpdate() {
        CoroutineScope(ioDispatcher).launch {
            remoteConfig.fetchAndActivate().await()
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

            preferences.updateFeature(configBoolean)
        }
    }
}