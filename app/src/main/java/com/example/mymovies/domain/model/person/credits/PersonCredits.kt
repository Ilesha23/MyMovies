package com.example.mymovies.domain.model.person.credits

data class PersonCredits(
    val cast: List<PersonCast>,
    val crew: List<PersonCrew>,
    val id: Int
)