package com.lelestacia.hayate.feature.anime.core.source.local.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.studio.AnimeStudioEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeStudioCrossReference

@Dao
internal interface StudioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudio(studio: AnimeStudioEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudios(studios: List<AnimeStudioEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReference(crossReference: AnimeStudioCrossReference)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReferences(crossReferences: List<AnimeStudioCrossReference>)

    @Query("SELECT * FROM studio WHERE mal_id = :malID LIMIT 1")
    suspend fun getStudioByID(malID: Int): AnimeStudioEntity

    @Query("SELECT * FROM studio")
    suspend fun getStudios(): List<AnimeStudioEntity>
}