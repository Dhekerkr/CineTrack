package com.example.cinetrack.data.api

import com.example.cinetrack.data.model.CastResponse
import com.example.cinetrack.data.model.MovieDetails
import com.example.cinetrack.data.model.MovieResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class APIServiceKtor {

    private val client = KtorClient.client
    private val apiKey = "bb6c56147af42131f2ef5443c7f862ba"

    private fun baseParams(builder: io.ktor.client.request.HttpRequestBuilder) {
        builder.parameter("api_key", apiKey)
        builder.parameter("language", "fr-FR")
    }

    suspend fun getPopularMovies(): MovieResponse {
        return client.get("movie/popular") {
            baseParams(this)
        }.body()
    }

    suspend fun searchMovies(query: String): MovieResponse {
        return client.get("search/movie") {
            baseParams(this)
            parameter("query", query)
        }.body()
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return client.get("movie/$movieId") {
            baseParams(this)
        }.body()
    }

    suspend fun getMovieCredits(movieId: Int): CastResponse {
        return client.get("movie/$movieId/credits") {
            baseParams(this)
        }.body()
    }
}
