package com.example.mymovies.data.remote.response.movie.images

data class MovieImagesDto(
    val backdrops: List<BackdropDto>?,
    val id: Int?,
    val logos: List<LogoDto>?,
    val posters: List<PosterDto>?
)