package com.example.mymovies.ui.movies

data class MovieListViewState(
    val popularFilter: Boolean = true,
    val upcomingFilter: Boolean = false,
    val topRatedFilter: Boolean = false
)
