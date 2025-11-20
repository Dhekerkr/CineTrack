package com.example.cinetrack.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinetrack.ui.screens.details.DetailsScreen
import com.example.cinetrack.ui.screens.favorites.FavoritesScreen
import com.example.cinetrack.ui.screens.home.HomeScreen
import com.example.cinetrack.ui.screens.search.SearchScreen
import com.example.cinetrack.viewmodel.FavoriteViewModel
import com.example.cinetrack.viewmodel.MovieViewModel
import com.example.cinetrack.viewmodel.RatingViewModel

@Composable
fun NavGraph(
    movieViewModel: MovieViewModel,
    favoriteViewModel: FavoriteViewModel,
    ratingViewModel: RatingViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(
                viewModel = movieViewModel,
                navController = navController
            )
        }

        composable("search?query={query}") { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""

            SearchScreen(
                viewModel = movieViewModel,
                navController = navController,
                query = query
            )
        }


        composable("favorites") {
            FavoritesScreen(
                viewModel = favoriteViewModel,
                navController = navController
            )
        }

        composable("details/{id}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("id")!!.toInt()

            DetailsScreen(
                viewModel = movieViewModel,
                favoriteViewModel = favoriteViewModel,
                ratingViewModel = ratingViewModel,
                navController = navController,
                movieId = movieId
            )
        }
    }
}
