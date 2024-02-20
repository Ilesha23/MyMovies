package com.example.mymovies.data.remote.response.movie.credits

data class CrewDto(
    val adult: Boolean? = false,
    val credit_id: String? = "",
    val department: String? = "",
    val gender: Int? = 1,
    val id: Int? = -1,
    val job: String? = "",
    val known_for_department: String? = "",
    val name: String? = "",
    val original_name: String? = "",
    val popularity: Double? = 0.0,
    val profile_path: String? =""
)