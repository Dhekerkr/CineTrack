package com.example.cinetrack.ui.screens.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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

@Composable
fun FavoritesScreen(
    viewModel: FavoriteViewModel,
    navController: NavHostController
) {
    val favorites by viewModel.favorites.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        // --- TOP BAR ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
                text = "⭐ My Favorites",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
        }

        if (favorites.isEmpty()) {
            // EMPTY STATE
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No favorite movies yet!",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
            }
        } else {
            // FAVORITES LIST
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favorites) { movie ->
                    FavoriteCard(
                        movie = movie,
                        onOpen = { navController.navigate("details/${movie.id}") },
                        onRemove = { viewModel.removeFavorite(movie.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteCard(
    movie: MovieEntity,
    onOpen: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F8F8)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {

            // MOVIE IMAGE
            Image(
                painter = rememberAsyncImagePainter(
                    "https://image.tmdb.org/t/p/w500${movie.posterPath}"
                ),
                contentDescription = movie.title,
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
            )

            Spacer(Modifier.width(12.dp))

            // MOVIE INFO
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    movie.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    movie.overview,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }

            Spacer(Modifier.width(12.dp))

            // REMOVE BUTTON
            IconButton(
                onClick = onRemove,
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFFFE6E6), CircleShape)
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Remove",
                    tint = Color(0xFFD50000)
                )
            }
        }
    }
}
