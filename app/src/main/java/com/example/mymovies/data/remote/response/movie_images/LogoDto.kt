package com.example.mymovies.data.remote.response.movie_images

data class LogoDto(
    val aspect_ratio: Double?,
    val file_path: String?,
    val height: Int?,
    val iso_639_1: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val width: Int?
)