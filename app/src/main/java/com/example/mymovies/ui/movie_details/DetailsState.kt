package com.example.mymovies.ui.movie_details

import com.example.mymovies.domain.model.movie_details.MovieDetails

data class DetailsState(
    val isLoading: Boolean = false,
    val details: MovieDetails?,
    val error: String? = null
)
