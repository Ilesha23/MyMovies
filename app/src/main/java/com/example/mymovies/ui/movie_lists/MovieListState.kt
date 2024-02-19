package com.example.mymovies.ui.movie_lists

import com.example.mymovies.domain.model.movie.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val list: List<Movie> = emptyList(),
    val error: Int? = null
)