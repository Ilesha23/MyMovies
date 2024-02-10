package com.example.mymovies.ui.details

import com.example.mymovies.domain.model.movie_credits.Crew

data class CrewState(
    val isLoading: Boolean = false,
    val crew: List<Crew> = emptyList(),
    val error: String? = null
)
