package com.example.cinetrack.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val vote_average: Double,
    val poster_path: String?,


)
