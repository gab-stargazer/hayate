package com.lelestacia.hayate.feature.anime.exploration.source.di

import com.lelestacia.hayate.feature.anime.exploration.source.Constant
import com.lelestacia.hayate.feature.anime.exploration.source.endpoint.ScheduleEndpoint
import com.lelestacia.hayate.feature.anime.exploration.source.endpoint.SeasonEndpoint
import com.lelestacia.hayate.feature.anime.exploration.source.endpoint.TopEndpoint
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
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
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