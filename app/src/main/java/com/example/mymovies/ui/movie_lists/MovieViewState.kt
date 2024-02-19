package com.example.mymovies.ui.movie_lists

data class MovieListViewState(
    val popularFilter: Boolean = true,
    val upcomingFilter: Boolean = false,
    val topRatedFilter: Boolean = false
)
