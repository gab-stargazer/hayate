package com.lelestacia.hayate.feature.anime.detail.ui.di

import com.lelestacia.hayate.common.shared.api.FeatureApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface DetailModule {

    @Binds
    @IntoSet
    fun bindDetailModuleApi(impl: DetailModuleApiImpl): FeatureApi
}