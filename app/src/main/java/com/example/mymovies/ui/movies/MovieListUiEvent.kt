package com.example.mymovies.ui.movies

sealed interface MovieListUiEvent {
    data object Paginate : MovieListUiEvent
}