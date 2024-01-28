package com.example.mymovies.ui.details

import com.example.mymovies.domain.model.movie_details.MovieDetails

data class DetailsState(
    val isLoading: Boolean = false,
    val details: MovieDetails?,
    val error: String? = null
)
