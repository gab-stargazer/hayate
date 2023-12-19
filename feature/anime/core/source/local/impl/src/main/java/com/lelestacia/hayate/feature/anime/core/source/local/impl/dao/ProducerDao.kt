package com.lelestacia.hayate.feature.anime.core.source.local.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.producer.AnimeProducerEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeProducerCrossReference

@Dao
interface ProducerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducer(producer: AnimeProducerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducers(producers: List<AnimeProducerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReference(crossReference: AnimeProducerCrossReference)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReferences(crossReferences: List<AnimeProducerCrossReference>)

    @Query("SELECT * FROM producer WHERE mal_id = :malID LIMIT 1")
    suspend fun getProducerByID(malID: Int): AnimeProducerEntity

    @Query("SELECT * FROM producer")
    suspend fun getProducers(): List<AnimeProducerEntity>
}