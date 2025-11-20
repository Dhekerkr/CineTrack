package com.example.cinetrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinetrack.data.db.MovieDao

class FavoriteViewModelFactory(
    private val dao: MovieDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModel(dao) as T
    }
}
