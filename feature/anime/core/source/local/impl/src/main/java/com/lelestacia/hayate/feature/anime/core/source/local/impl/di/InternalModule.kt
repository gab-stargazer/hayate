package com.lelestacia.hayate.feature.anime.core.source.local.impl.di

import android.content.Context
import androidx.room.Room
import com.lelestacia.hayate.common.shared.util.IoDispatcher
import com.lelestacia.hayate.feature.anime.core.source.local.api.api.AnimeLocalDataSourceApi
import com.lelestacia.hayate.feature.anime.core.source.local.impl.AnimeLocalDataSourceImpl
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.AnimeDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.DemographicDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.ExplicitGenreDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.GenreDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.LicensorDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.ProducerDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.StudioDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.ThemeDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.TitleDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.dao.WatchlistDao
import com.lelestacia.hayate.feature.anime.core.source.local.impl.db.AnimeDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
internal object InternalModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AnimeDB {
        return Room
            .databaseBuilder(context, AnimeDB::class.java, "hayate-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimeDao(db: AnimeDB): AnimeDao {
        return db.animeDao()
    }

    @Provides
    @Singleton
    fun provideDemographicDao(db: AnimeDB): DemographicDao {
        return db.animeDemographicDao()
    }

    @Provides
    @Singleton
    fun provideExplicitGenreDao(db: AnimeDB): ExplicitGenreDao {
        return db.animeExplicitGenreDao()
    }

    @Provides
    @Singleton
    fun provideGenreDao(db: AnimeDB): GenreDao {
        return db.animeGenreDao()
    }

    @Provides
    @Singleton
    fun provideLicensorDao(db: AnimeDB): LicensorDao {
        return db.licensorDao()
    }

    @Provides
    @Singleton
    fun provideProducerDao(db: AnimeDB): ProducerDao {
        return db.producerDao()
    }

    @Provides
    @Singleton
    fun provideStudioDao(db: AnimeDB): StudioDao {
        return db.studioDao()
    }

    @Provides
    @Singleton
    fun provideThemeDao(db: AnimeDB): ThemeDao {
        return db.themeDao()
    }

    @Provides
    @Singleton
    fun provideWatchlistDao(db: AnimeDB): WatchlistDao {
        return db.watchlistDao()
    }

    @Provides
    @Singleton
    fun provideTitleDao(db: AnimeDB): TitleDao {
        return db.titleDao()
    }

    @Provides
    @Singleton
    fun provideDataSource(
        animeDao: AnimeDao,
        genreDao: GenreDao,
        explicitGenreDao: ExplicitGenreDao,
        licensorDao: LicensorDao,
        producerDao: ProducerDao,
        studioDao: StudioDao,
        themeDao: ThemeDao,
        titleDao: TitleDao,
        demographicDao: DemographicDao,
        watchlistDao: WatchlistDao,
        @IoDispatcher ioDispatcher: CoroutineContext,
    ): AnimeLocalDataSourceApi {
        return AnimeLocalDataSourceImpl(
            animeDao = animeDao,
            genreDao = genreDao,
            explicitGenreDao = explicitGenreDao,
            licensorDao = licensorDao,
            producerDao = producerDao,
            studioDao = studioDao,
            themeDao = themeDao,
            titleDao = titleDao,
            demographicDao = demographicDao,
            watchlistDao = watchlistDao,
            ioDispatcher = ioDispatcher
        )
    }
}