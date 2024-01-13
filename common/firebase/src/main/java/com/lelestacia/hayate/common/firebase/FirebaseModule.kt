package com.lelestacia.hayate.common.firebase

import com.lelestacia.hayate.common.preferences.ConfigPreferences
import com.lelestacia.hayate.common.shared.util.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseConfigService(
        preferences: ConfigPreferences,
        @IoDispatcher dispatcher: CoroutineContext,
    ): HayateFirebaseRemoteConfig {
        return HayateFirebaseRemoteConfig(
            preferences = preferences,
            ioDispatcher = dispatcher
        )
    }
}