package com.lelestacia.hayate.feature.anime.core.source.local.impl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.watchlist.WatchListEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WatchlistDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWatchList(watchList: WatchListEntity)

    @Delete
    suspend fun deleteWatchList(watchList: WatchListEntity)

    @Query("SELECT * FROM watchlist WHERE anime_id = :animeId")
    fun getWatchlistByID(animeId: Int): Flow<WatchListEntity?>

    /*
     *  TODO: Implement the rest of dao here since i have no idea right now
     */
}