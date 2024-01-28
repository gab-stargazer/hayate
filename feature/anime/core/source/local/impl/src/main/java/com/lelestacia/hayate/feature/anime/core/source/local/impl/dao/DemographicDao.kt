package com.lelestacia.hayate.feature.anime.core.source.local.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.demographic.AnimeDemographicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeDemographicCrossReference

@Dao
internal interface DemographicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDemographic(demographic: AnimeDemographicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDemographics(demographics: List<AnimeDemographicEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReference(crossReference: AnimeDemographicCrossReference)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReferences(crossReferences: List<AnimeDemographicCrossReference>)

    @Query("SELECT * FROM demographic WHERE mal_id = :malID LIMIT 1")
    suspend fun getDemographicByID(malID: Int): AnimeDemographicEntity

    @Query("SELECT * FROM demographic")
    suspend fun getDemographics(): List<AnimeDemographicEntity>
}