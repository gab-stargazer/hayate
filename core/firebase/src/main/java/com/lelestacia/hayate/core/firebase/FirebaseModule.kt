package com.lelestacia.hayate.core.firebase

import com.lelestacia.hayate.core.common.util.IoDispatcher
import com.lelestacia.hayate.core.preferences.ConfigPreferences
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