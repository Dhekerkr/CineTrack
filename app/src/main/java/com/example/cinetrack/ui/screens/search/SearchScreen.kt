package com.example.cinetrack.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.cinetrack.viewmodel.MovieViewModel

@Composable
fun SearchScreen(
    viewModel: MovieViewModel,
    navController: NavHostController,
    query: String
) {
    var searchText by remember { mutableStateOf(query) }
    val results = viewModel.searchResults.collectAsState().value

    // Automatically trigger search if query came from HomeScreen
    LaunchedEffect(Unit) {
        if (query.isNotEmpty()) {
            viewModel.searchMovies(query)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // --- TOP BAR ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                "Search Movies",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(Modifier.height(12.dp))

        // --- SEARCH BAR ---
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search")
            },
            placeholder = { Text("Search a movie…") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(Modifier.height(8.dp))

        // --- SEARCH BUTTON ---
        Button(
            onClick = { viewModel.searchMovies(searchText) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Search")
        }

        Spacer(Modifier.height(16.dp))

        if (searchText.isNotEmpty()) {
            Text(
                text = "Here are the results for: \"$searchText\"",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(12.dp))

        // --- RESULTS LIST ---
        LazyColumn {
            items(results.size) { index ->
                val movie = results[index]

                MovieCard(
                    title = movie.title,
                    imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                    overview = movie.overview,
                    onClick = { navController.navigate("details/${movie.id}") }
                )

                Spacer(Modifier.height(12.dp))
            }
        }
    }
}


@Composable
fun MovieCard(title: String, imageUrl: String, overview: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {

            // IMAGE
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = title,
                modifier = Modifier.size(110.dp)
            )

            Spacer(Modifier.width(12.dp))

            // TEXTS
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = overview,
                    maxLines = 3,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }
        }
    }
}
