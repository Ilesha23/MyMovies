package com.example.mymovies.data.remote.response.movie.credits

data class CastDto(
    val adult: Boolean? = false,
    val cast_id: Int? = -1,
    val character: String? = "",
    val credit_id: String? = "",
    val gender: Int? = 1,
    val id: Int? = -1,
    val known_for_department: String? = "",
    val name: String? = "",
    val order: Int? = -1,
    val original_name: String? = "",
    val popularity: Double? = 0.0,
    val profile_path: String? = ""
)