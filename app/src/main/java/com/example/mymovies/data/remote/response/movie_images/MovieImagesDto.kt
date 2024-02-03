package com.example.mymovies.data.remote.response.movie_images

data class MovieImagesDto(
    val backdrops: List<BackdropDto>?,
    val id: Int?,
    val logos: List<LogoDto>?,
    val posters: List<PosterDto>?
)