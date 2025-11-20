package com.example.cinetrack.ui.screens.home

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.cinetrack.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MovieViewModel,
    navController: NavHostController
) {
    val movies = viewModel.popularMovies.collectAsState().value

    LaunchedEffect(Unit) {
        if (movies.isEmpty()) viewModel.loadPopularMovies()
    }

    var showSearchBar by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // --- HEADER ---
        Text(
            text = "🎬 Welcome to CineTrack",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Find your next movie 🎞️",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(24.dp))

        // --- SEARCH ICON ---
        IconButton(
            onClick = { showSearchBar = !showSearchBar },
            modifier = Modifier
                .size(55.dp)
                .align(Alignment.End)
        ) {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }

        // --- SEARCH BAR ---
        AnimatedVisibility(
            visible = showSearchBar,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search movies…") },
                    leadingIcon = {
                        Icon(Icons.Outlined.Search, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(Modifier.height(10.dp))

                Button(
                    onClick = {
                        if (searchQuery.isNotEmpty()) {
                            navController.navigate("search?query=$searchQuery")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Search")
                }

                Spacer(Modifier.height(20.dp))
            }
        }

        // --- SUGGESTIONS ---
        Text(
            "✨ Trending Movies",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(Modifier.height(12.dp))

        if (movies.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(movies.shuffled().take(10)) { movie ->
                    TrendingMovieCard(
                        imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                        title = movie.title,
                        onClick = { navController.navigate("details/${movie.id}") }
                    )
                }
            }
        }
    }
}

@Composable
fun TrendingMovieCard(
    imageUrl: String,
    title: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier
                .height(210.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(Modifier.height(8.dp))

        Text(
            title,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}
