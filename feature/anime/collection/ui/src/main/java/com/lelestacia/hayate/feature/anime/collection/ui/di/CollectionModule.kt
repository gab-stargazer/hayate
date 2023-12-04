package com.lelestacia.hayate.feature.anime.collection.ui.di

import com.lelestacia.hayate.common.shared.api.FeatureApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface CollectionModule {

    @Binds
    @IntoSet
    fun bindCollectionModuleApi(impl: CollectionModuleApiImpl): FeatureApi
}