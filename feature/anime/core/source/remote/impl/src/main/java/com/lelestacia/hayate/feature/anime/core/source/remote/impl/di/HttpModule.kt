package com.lelestacia.hayate.feature.anime.core.source.remote.impl.di

import android.content.Context
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.BuildConfig
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.ConnectivityChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object HttpModule {

    @Provides
    @Singleton
    fun provideSSL(): CertificatePinner = CertificatePinner.Builder()
        .add(HOSTNAME, BuildConfig.SHA_1)
        .add(HOSTNAME, BuildConfig.SHA_2)
        .add(HOSTNAME, BuildConfig.SHA_3)
        .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )

    @Provides
    @Singleton
    @Named(ONLINE_INTERCEPTOR)
    fun provideOnlineInterceptor(): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60 * 60
        response
            .newBuilder()
            .header(CACHE_CONTROL, "public, max-age=$maxAge")
            .build()
    }

    @Provides
    @Singleton
    @Named(OFFLINE_INTERCEPTOR)
    fun provideOfflineInterceptor(@ApplicationContext mContext: Context): Interceptor =
        Interceptor { chain ->
            var request = chain.request()
            val connectivityChecker = ConnectivityChecker()
            val isConnectivityAvailable = connectivityChecker(mContext)
            if (!isConnectivityAvailable) {
                val maxStale = 60 * 60 * 24 * 7
                request = request
                    .newBuilder()
                    .header(CACHE_CONTROL, "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader(PRAGMA)
                    .build()
            }
            chain.proceed(request)
        }

    @Provides
    @Singleton
    fun provideOkHttp(
        loggingInterceptor: HttpLoggingInterceptor,
        @Named(ONLINE_INTERCEPTOR) internetInterceptor: Interceptor,
        @Named(OFFLINE_INTERCEPTOR) offlineInterceptor: Interceptor,
        shaKey: CertificatePinner,
        @ApplicationContext mContext: Context,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .cache(Cache(mContext.cacheDir, CACHE_SIZE))
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(internetInterceptor)
            .addInterceptor(loggingInterceptor)
            .certificatePinner(shaKey)
            .build()

    private const val CACHE_SIZE: Long = (50 * 1024 * 1024).toLong()
    private const val ONLINE_INTERCEPTOR = "online_interceptor"
    private const val OFFLINE_INTERCEPTOR = "offline_interceptor"
    private const val CACHE_CONTROL = "Cache-Control"
    private const val PRAGMA = "Pragma"
    private const val HOSTNAME = "api.jikan.moe"
}