package com.lelestacia.hayate.feature.anime.core.source.local.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.genre.AnimeExplicitGenreEntity
import com.lelestacia.hayate.feature.anime.core.source.local.impl.entity.cross_reference.AnimeExplicitGenreCrossReference

@Dao
internal interface ExplicitGenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExplicitGenre(explicitGenre: AnimeExplicitGenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExplicitGenres(explicitGenres: List<AnimeExplicitGenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReference(crossReference: AnimeExplicitGenreCrossReference)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReferences(crossReferences: List<AnimeExplicitGenreCrossReference>)

    @Query("SELECT * FROM explicit_genre WHERE mal_id = :malID LIMIT 1")
    suspend fun getExplicitGenreByID(malID: Int): AnimeExplicitGenreEntity

    @Query("SELECT * FROM explicit_genre")
    suspend fun getExplicitGenres(): List<AnimeExplicitGenreEntity>
}