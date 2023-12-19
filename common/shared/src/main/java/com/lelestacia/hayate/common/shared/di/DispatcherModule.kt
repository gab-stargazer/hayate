package com.lelestacia.hayate.common.shared.di

import com.lelestacia.hayate.common.shared.util.DefaultDispatcher
import com.lelestacia.hayate.common.shared.util.IoDispatcher
import com.lelestacia.hayate.common.shared.util.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineContext = Dispatchers.IO

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineContext = Dispatchers.Default

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineContext = Dispatchers.Main
}