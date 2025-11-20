package com.example.cinetrack.data.model

import kotlinx.serialization.Serializable
@Serializable
data class CastResponse(
    val cast: List<Actor>
)
@Serializable
data class Actor(
    val id: Int,
    val name: String,
    val profile_path: String?
)
