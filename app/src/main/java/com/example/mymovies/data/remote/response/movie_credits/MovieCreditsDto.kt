package com.example.mymovies.data.remote.response.movie_credits

data class MovieCreditsDto(
    val cast: List<CastDto>? = emptyList(),
    val crew: List<CrewDto>? = emptyList(),
    val id: Int? = -1
)