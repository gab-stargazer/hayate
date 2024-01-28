package com.lelestacia.hayate.feature.anime.core.source.remote.impl.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [SourceModule::class])
@InstallIn(SingletonComponent::class)
interface ExposedRemoteDataSourceModule