package com.example.cinetrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cinetrack.data.db.MovieDatabase
import com.example.cinetrack.data.repository.RatingRepository
import com.example.cinetrack.ui.navigation.NavGraph
import com.example.cinetrack.ui.theme.CineTrackTheme
import com.example.cinetrack.viewmodel.FavoriteViewModel
import com.example.cinetrack.viewmodel.MovieViewModel
import com.example.cinetrack.viewmodel.RatingViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Database
        val db = MovieDatabase.getDatabase(this)
        val dao = db.movieDao()

        // ViewModels
        val movieViewModel = MovieViewModel()   // no parameters
        val favoriteViewModel = FavoriteViewModel(dao)
        val ratingRepository = RatingRepository(db.ratingDao())
        val ratingViewModel = RatingViewModel(ratingRepository)

        setContent {
            CineTrackTheme {
                NavGraph(
                    movieViewModel = movieViewModel,
                    favoriteViewModel = favoriteViewModel,
                    ratingViewModel = ratingViewModel
                )
            }
        }
    }
}
