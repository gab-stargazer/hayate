package com.lelestacia.hayate.feature.anime.core.source.remote.impl_test

import com.lelestacia.hayate.feature.anime.core.source.remote.api.AnimeRemoteDataSourceApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestSourceModule {


    @Provides
    @Singleton
    fun provideAnimeDataSource(): AnimeRemoteDataSourceApi {
        return AnimeRemoteDataSourceImplTest()
    }
}