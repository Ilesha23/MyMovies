package com.example.mymovies.domain.model.movie_images

data class MovieImages(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)