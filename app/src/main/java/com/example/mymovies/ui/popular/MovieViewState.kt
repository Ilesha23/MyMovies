package com.example.mymovies.ui.popular

data class MovieListViewState(
    val popularFilter: Boolean = true,
    val upcomingFilter: Boolean = false,
    val topRatedFilter: Boolean = false
)
