package com.example.mymovies.ui.movie_lists

sealed interface MovieListUiEvent {
    data object Paginate : MovieListUiEvent
}