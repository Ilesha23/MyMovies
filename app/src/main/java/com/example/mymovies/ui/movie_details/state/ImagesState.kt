package com.example.mymovies.ui.movie_details.state

data class ImagesState(
    val isLoading: Boolean = false,
    val images: List<String> = emptyList(),
    val error: String? = null
)
