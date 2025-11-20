package com.example.cinetrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinetrack.data.repository.RatingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RatingViewModel(private val repo: RatingRepository): ViewModel() {

    private val _averageRating = MutableStateFlow<Double?>(null)
    val averageRating: StateFlow<Double?> = _averageRating

    fun loadAverageRating(movieId: Int) {
        viewModelScope.launch {
            repo.getAverageRating(movieId).collect {
                _averageRating.value = it
            }
        }
    }

    fun submitRating(movieId: Int, rating: Int) {
        viewModelScope.launch {
            repo.addRating(movieId, rating)
            loadAverageRating(movieId) // Refresh immediately
        }
    }
}
