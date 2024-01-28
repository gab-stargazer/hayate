package com.lelestacia.hayate.feature.anime.collection.domain.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module(includes = [InternalModule::class])
@InstallIn(ViewModelComponent::class)
interface ExposedModule