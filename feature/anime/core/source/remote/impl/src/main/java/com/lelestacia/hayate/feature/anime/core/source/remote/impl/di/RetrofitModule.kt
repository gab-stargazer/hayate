package com.lelestacia.hayate.feature.anime.core.source.remote.impl.di

import com.lelestacia.hayate.feature.anime.core.source.remote.impl.Constant.BASE_URL
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.ScheduleEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.SeasonEndpoint
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.TopEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideTopEndpoint(
        retrofit: Retrofit
    ): TopEndpoint {
        return retrofit.create(TopEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideSeasonEndpoint(
        retrofit: Retrofit
    ): SeasonEndpoint {
        return retrofit.create(SeasonEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduleEndpoint(
        retrofit: Retrofit
    ): ScheduleEndpoint {
        return retrofit.create(ScheduleEndpoint::class.java)
    }
}