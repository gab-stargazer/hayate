package com.lelestacia.hayate.feature.anime.core.source.local.impl.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalModule::class])
@InstallIn(SingletonComponent::class)
interface ExposedLocalDataSourceModule