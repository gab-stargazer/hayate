package com.lelestacia.hayate.feature.anime.core.source.local.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.watchlist.WatchListEntity

@Dao
internal interface WatchlistDao {

    @Insert
    suspend fun insertWatchList(watchList: WatchListEntity)

    /*
     *  TODO: Implement the rest of dao here since i have no idea right now
     */
}