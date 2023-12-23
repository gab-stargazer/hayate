package com.lelestacia.hayate.feature.anime.core.source.local.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.anime.AnimeTitleSynonymEntity

@Dao
interface TitleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTitles(titles: List<AnimeTitleSynonymEntity>)
}