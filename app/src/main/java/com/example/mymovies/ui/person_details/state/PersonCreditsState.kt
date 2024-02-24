package com.example.mymovies.ui.person_details.state

import com.example.mymovies.domain.model.person.credits.PersonCast
import com.example.mymovies.domain.model.person.credits.PersonCrew

data class PersonCreditsState(
    val isLoading: Boolean = false,
    val crew: List<PersonCrew>? = null,
    val cast: List<PersonCast>? = null,
    val error: String? = null
)
