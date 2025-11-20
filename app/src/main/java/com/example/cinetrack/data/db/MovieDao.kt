package com.example.cinetrack.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: MovieEntity)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT * FROM favorites WHERE id = :id LIMIT 1")
    suspend fun isFavorite(id: Int): MovieEntity?
}
