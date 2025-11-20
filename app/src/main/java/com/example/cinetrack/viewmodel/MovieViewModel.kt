package com.example.cinetrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinetrack.data.model.Actor
import com.example.cinetrack.data.model.Movie
import com.example.cinetrack.data.model.MovieDetails
import com.example.cinetrack.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repo = MovieRepository()

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies

    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
    val searchResults: StateFlow<List<Movie>> = _searchResults

    fun loadPopularMovies() {
        viewModelScope.launch {
            val response = repo.getPopularMovies()
            _popularMovies.value = response.results
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            val response = repo.searchMovies(query)
            _searchResults.value = response.results
        }
    }

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: StateFlow<MovieDetails?> = _movieDetails

    private val _movieCast = MutableStateFlow<List<Actor>>(emptyList())
    val movieCast: StateFlow<List<Actor>> = _movieCast

    fun loadMovieDetails(id: Int) {
        viewModelScope.launch {
            _movieDetails.value = repo.getMovieDetails(id)
            _movieCast.value = repo.getMovieCredits(id).cast
        }
    }

}
