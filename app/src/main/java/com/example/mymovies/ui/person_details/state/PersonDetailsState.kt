package com.example.mymovies.ui.person_details.state

import com.example.mymovies.domain.model.person_details.PersonDetails

data class PersonDetailsState(
    val isLoading: Boolean = false,
    val details: PersonDetails?,
    val images: List<String> = emptyList(),
    val error: String? = null
)
