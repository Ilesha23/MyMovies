package com.example.mymovies.domain.model.movie.credits

data class MovieCredits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)