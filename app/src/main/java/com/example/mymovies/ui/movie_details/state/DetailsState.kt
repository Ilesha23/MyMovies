package com.example.mymovies.ui.movie_details.state

import com.example.mymovies.domain.model.movie.details.MovieDetails

data class DetailsState(
    val isLoading: Boolean = false,
    val details: MovieDetails?,
    val error: String? = null
)
