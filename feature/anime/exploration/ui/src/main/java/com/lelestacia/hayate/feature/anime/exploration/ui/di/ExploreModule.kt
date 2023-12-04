package com.lelestacia.hayate.feature.anime.exploration.ui.di

import com.lelestacia.hayate.common.shared.api.FeatureApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
interface ExploreModule {

    @Binds
    @IntoSet
    fun bindExploreModuleApi(impl: ExploreModuleApiImpl): FeatureApi
}