package com.lelestacia.hayate.feature.anime.core.source.local.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeBasicEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.AnimeFullEntity

@Dao
internal interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeBasicEntity)

    @Transaction
    @Query("SELECT * FROM anime WHERE mal_id =:malId LIMIT 1")
    suspend fun getAnimeByAnimeId(malId: Int): AnimeFullEntity
}