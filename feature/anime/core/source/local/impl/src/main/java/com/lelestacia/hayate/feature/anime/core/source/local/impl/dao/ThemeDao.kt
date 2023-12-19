package com.lelestacia.hayate.feature.anime.core.source.local.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.theme.AnimeThemeEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeThemeCrossReference

@Dao
internal interface ThemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTheme(theme: AnimeThemeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThemes(themes: List<AnimeThemeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReference(crossReference: AnimeThemeCrossReference)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReferences(crossReferences: List<AnimeThemeCrossReference>)

    @Query("SELECT * FROM theme WHERE mal_id = :malID LIMIT 1")
    suspend fun getThemeByID(malID: Int): AnimeThemeEntity

    @Query("SELECT * FROM theme")
    suspend fun getThemes(): List<AnimeThemeEntity>
}