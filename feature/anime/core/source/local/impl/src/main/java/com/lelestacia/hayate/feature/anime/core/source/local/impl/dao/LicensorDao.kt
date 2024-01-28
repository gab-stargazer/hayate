package com.lelestacia.hayate.feature.anime.core.source.local.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.licensor.AnimeLicensorEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeLicensorCrossReference

@Dao
internal interface LicensorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLicensor(licensor: AnimeLicensorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLicensors(licensors: List<AnimeLicensorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReference(crossReference: AnimeLicensorCrossReference)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReferences(crossReferences: List<AnimeLicensorCrossReference>)

    @Query("SELECT * FROM licensor WHERE mal_id = :malID LIMIT 1")
    suspend fun getLicensorByID(malID: Int): AnimeLicensorEntity

    @Query("SELECT * FROM licensor")
    suspend fun getLicensors(): List<AnimeLicensorEntity>
}