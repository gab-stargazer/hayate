package com.lelestacia.hayate.feature.anime.search.ui.di

import com.lelestacia.hayate.common.shared.api.FeatureApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalModule {

    @Binds
    @IntoSet
    fun provideSearchFeature(feature: FeatureImplementation): FeatureApi
}