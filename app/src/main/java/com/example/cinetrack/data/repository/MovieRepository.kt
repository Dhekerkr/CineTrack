package com.example.cinetrack.data.repository

import com.example.cinetrack.data.api.APIServiceKtor
import com.example.cinetrack.data.model.CastResponse
import com.example.cinetrack.data.model.MovieDetails
import com.example.cinetrack.data.model.MovieResponse

class MovieRepository {

    private val api = APIServiceKtor()

    suspend fun getPopularMovies(): MovieResponse {
        return api.getPopularMovies()
    }

    suspend fun searchMovies(query: String): MovieResponse {
        return api.searchMovies(query)
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return api.getMovieDetails(movieId)
    }

    suspend fun getMovieCredits(movieId: Int): CastResponse {
        return api.getMovieCredits(movieId)
    }
}
