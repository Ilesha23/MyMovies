package com.example.mymovies.ui.popular

sealed interface MovieListUiEvent {
    data object Paginate : MovieListUiEvent
}