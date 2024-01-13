package com.lelestacia.hayate.application

import android.app.Application
import com.lelestacia.hayate.common.firebase.HayateFirebaseRemoteConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class HayateApp : Application() {

    @Inject
    lateinit var firebaseRemoteConfig: HayateFirebaseRemoteConfig

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        firebaseRemoteConfig.listenToUpdate()
    }


}