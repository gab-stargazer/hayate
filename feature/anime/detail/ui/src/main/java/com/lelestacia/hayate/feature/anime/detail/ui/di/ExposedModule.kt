package com.lelestacia.hayate.feature.anime.detail.ui.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalModule::class])
@InstallIn(SingletonComponent::class)
interface ExposedModule