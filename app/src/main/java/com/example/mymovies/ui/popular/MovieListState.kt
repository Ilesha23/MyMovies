package com.example.mymovies.ui.popular

import com.example.mymovies.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val list: List<Movie> = emptyList()
)
