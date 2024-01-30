package com.example.mymovies.data.remote.response.movie_images

data class MovieImagesDto(
    val backdrops: List<Backdrop>?,
    val id: Int?,
    val logos: List<Logo>?,
    val posters: List<Poster>?
)