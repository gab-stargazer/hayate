package com.lelestacia.hayate.feature.anime.core.source.local.impl.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.demographic.AnimeDemographicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeExplicitGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.licensor.AnimeLicensorEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.producer.AnimeProducerEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.studio.AnimeStudioEntity
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.theme.AnimeThemeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.converter.AnimeAiredConverter
import com.lelestacia.hayate.feature.anime.core.source.local.impl.converter.AnimeImageConverter
import com.lelestacia.hayate.feature.anime.core.source.local.impl.converter.AnimeTitlesConverter
import com.lelestacia.hayate.feature.anime.core.source.local.impl.converter.AnimeTrailerConverter
import com.lelestacia.hayate.feature.anime.core.source.local.impl.converter.DateConverter
import com.lelestacia.hayate.feature.anime.core.source.local.impl.converter.ListOfStringConverter
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
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.anime.AnimeTitleSynonymEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeDemographicCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeExplicitGenreCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeGenreCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeLicensorCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeProducerCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeStudioCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeThemeCrossReference
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.watchlist.WatchListEntity

@Database(
    entities = [
        AnimeDemographicEntity::class,
        AnimeGenreEntity::class,
        AnimeExplicitGenreEntity::class,
        AnimeLicensorEntity::class,
        AnimeProducerEntity::class,
        AnimeStudioEntity::class,
        AnimeThemeEntity::class,
        AnimeTitleSynonymEntity::class,
        WatchListEntity::class,
        AnimeDemographicCrossReference::class,
        AnimeGenreCrossReference::class,
        AnimeExplicitGenreCrossReference::class,
        AnimeLicensorCrossReference::class,
        AnimeProducerCrossReference::class,
        AnimeStudioCrossReference::class,
        AnimeThemeCrossReference::class,
        AnimeBasicEntity::class
    ],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2
        )
    ]
)
@TypeConverters(
    value = [
        AnimeAiredConverter::class,
        AnimeImageConverter::class,
        AnimeTitlesConverter::class,
        AnimeTrailerConverter::class,
        DateConverter::class,
        ListOfStringConverter::class
    ]
)
internal abstract class AnimeDB : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
    abstract fun animeDemographicDao(): DemographicDao
    abstract fun animeExplicitGenreDao(): ExplicitGenreDao
    abstract fun animeGenreDao(): GenreDao
    abstract fun licensorDao(): LicensorDao
    abstract fun producerDao(): ProducerDao
    abstract fun studioDao(): StudioDao
    abstract fun themeDao(): ThemeDao
    abstract fun watchlistDao(): WatchlistDao
    abstract fun titleDao(): TitleDao
}