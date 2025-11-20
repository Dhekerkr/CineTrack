package com.example.cinetrack.data.repository

import com.example.cinetrack.data.db.RatingDao
import com.example.cinetrack.data.db.RatingEntity
import kotlinx.coroutines.flow.Flow

class RatingRepository(private val dao: RatingDao) {

    suspend fun addRating(movieId: Int, ratingValue: Int) {
        dao.insertRating(RatingEntity(movieId = movieId, rating = ratingValue))
    }

    fun getAverageRating(movieId: Int): Flow<Double?> {
        return dao.getAverageRating(movieId)
    }
}
