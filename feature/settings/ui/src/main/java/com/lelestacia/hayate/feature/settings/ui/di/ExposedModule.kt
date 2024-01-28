package com.lelestacia.hayate.feature.settings.ui.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalModule::class])
@InstallIn(SingletonComponent::class)
interface ExposedModule