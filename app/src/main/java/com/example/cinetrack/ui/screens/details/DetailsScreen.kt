package com.example.cinetrack.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.cinetrack.data.db.MovieEntity
import com.example.cinetrack.viewmodel.FavoriteViewModel
import com.example.cinetrack.viewmodel.MovieViewModel
import com.example.cinetrack.viewmodel.RatingViewModel

@Composable
fun DetailsScreen(
    viewModel: MovieViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController,
    ratingViewModel: RatingViewModel,
    movieId: Int
) {
    viewModel.loadMovieDetails(movieId)

    val movie = viewModel.movieDetails.collectAsState().value

    var isFavorite by remember { mutableStateOf(false) }

    // Load rating average
    LaunchedEffect(movieId) {
        ratingViewModel.loadAverageRating(movieId)
        isFavorite = favoriteViewModel.isFavorite(movieId)
    }

    val averageRating = ratingViewModel.averageRating.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ){

        // --- TOP BAR WITH BACK BUTTON ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFEDEDED), CircleShape)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Spacer(Modifier.width(12.dp))

            Text(
                "Movie Details",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
        }

        movie?.let { m ->

            Spacer(modifier = Modifier.height(16.dp))

            // IMAGE
            Image(
                painter = rememberAsyncImagePainter(
                    "https://image.tmdb.org/t/p/w500${m.poster_path}"
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(Color.LightGray, RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            // TITLE
            Text(
                m.title,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // OVERVIEW
            Text(
                m.overview,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // FAVORITE BUTTON (dynamic)
            Button(
                onClick = {
                    if (!isFavorite) {
                        val entity = MovieEntity(
                            id = m.id,
                            title = m.title,
                            overview = m.overview,
                            posterPath = m.poster_path
                        )
                        favoriteViewModel.addFavorite(entity)
                        isFavorite = true
                    } else {
                        favoriteViewModel.removeFavorite(m.id)
                        isFavorite = false
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isFavorite) Color(0xFF6200EA) else Color(0xFFD50000)
                )
            ) {
                Icon(
                    imageVector = if (!isFavorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    if (!isFavorite) "Add to Favorites" else "Remove from Favorites",
                    color = Color.White
                )
            }

            Spacer(Modifier.height(16.dp))

            // Go to favorites button
            OutlinedButton(
                onClick = { navController.navigate("favorites") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Star, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Go to Favorites ⭐")
            }

            Spacer(Modifier.height(25.dp))

            // --- AVERAGE RATING SECTION ---
            Text(
                "Average Rating ⭐",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(30.dp)
                )
                Spacer(Modifier.width(8.dp))

                Text(
                    text = averageRating?.let { String.format("%.1f / 5", it) } ?: "No ratings yet",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            Spacer(Modifier.height(25.dp))

            // --- RATING SECTION ---
            Text(
                "Rate this movie ⭐",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(Modifier.height(10.dp))

            var rating by remember { mutableStateOf(0) }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 1..5) {
                    IconButton(onClick = { rating = i }) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = if (rating >= i) Color(0xFFFFC107) else Color.Gray,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    ratingViewModel.submitRating(m.id, rating)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Rating")
            }
        }
    }
}
