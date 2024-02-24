package com.example.mymovies.data.remote.response.person.credits

data class PersonCreditsDto(
    val cast: List<PersonCastDto>?,
    val crew: List<PersonCrewDto>?,
    val id: Int?
)