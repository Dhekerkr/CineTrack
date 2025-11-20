package com.example.cinetrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinetrack.data.db.MovieDao
import com.example.cinetrack.data.db.MovieEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoriteViewModel(private val dao: MovieDao) : ViewModel() {

    val favorites = dao.getAllFavorites().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addFavorite(movie: MovieEntity) {
        viewModelScope.launch {
            dao.insertFavorite(movie)
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            dao.deleteFavorite(id)
        }
    }

    suspend fun isFavorite(id: Int): Boolean {
        return dao.isFavorite(id) != null
    }
}
