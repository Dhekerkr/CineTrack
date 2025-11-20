package com.example.cinetrack.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRating(rating: RatingEntity)

    @Query("SELECT AVG(rating) FROM ratings WHERE movieId = :movieId")
    fun getAverageRating(movieId: Int): Flow<Double?>

    @Query("SELECT * FROM ratings WHERE movieId = :movieId")
    suspend fun getRatings(movieId: Int): List<RatingEntity>
}
