package com.example.mymovies.ui.person_details.state

data class PersonImagesState(
    val isLoading: Boolean = false,
    val images: List<String> = emptyList(),
    val error: String? = null
)
