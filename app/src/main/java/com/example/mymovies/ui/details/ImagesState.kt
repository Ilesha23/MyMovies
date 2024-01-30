package com.example.mymovies.ui.details

data class ImagesState(
    val isLoading: Boolean = false,
    val images: List<String> = emptyList(),
    val error: String? = null
)
