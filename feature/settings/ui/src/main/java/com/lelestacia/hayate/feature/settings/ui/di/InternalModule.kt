package com.lelestacia.hayate.feature.settings.ui.di

import com.lelestacia.hayate.core.common.api.FeatureApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalModule {

    @IntoSet
    @Binds
    fun provideMoreFeature(feature: MoreFeatureImplementation): FeatureApi
}