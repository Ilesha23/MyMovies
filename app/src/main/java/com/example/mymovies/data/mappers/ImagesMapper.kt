package com.example.mymovies.data.mappers

import com.example.mymovies.data.remote.response.movie_images.BackdropDto
import com.example.mymovies.data.remote.response.movie_images.LogoDto
import com.example.mymovies.data.remote.response.movie_images.MovieImagesDto
import com.example.mymovies.data.remote.response.movie_images.PosterDto
import com.example.mymovies.domain.model.movie_images.Backdrop
import com.example.mymovies.domain.model.movie_images.Logo
import com.example.mymovies.domain.model.movie_images.MovieImages
import com.example.mymovies.domain.model.movie_images.Poster

fun MovieImagesDto.toMovieImages() = MovieImages(
    backdrops?.map { it.toBackDrop() } ?: emptyList(),
    id ?: -1,
    logos?.map { it.toLogo() } ?: emptyList(),
    posters?.map { it.toPoster() } ?: emptyList()
)

fun BackdropDto.toBackDrop() = Backdrop(
    aspect_ratio ?: 0.0,
    file_path ?: "",
    height ?: 0,
    iso_639_1 ?: "",
    vote_average ?: 0.0,
    vote_count ?: 0,
    width ?: 0
)

fun LogoDto.toLogo() = Logo(
    aspect_ratio ?: 0.0,
    file_path ?: "",
    height ?: 0,
    iso_639_1 ?: "",
    vote_average ?: 0.0,
    vote_count ?: 0,
    width ?:0
)

fun PosterDto.toPoster() = Poster(
    aspect_ratio ?: 0.0,
    file_path ?: "",
    height ?: 0,
    iso_639_1 ?: "",
    vote_average ?: 0.0,
    vote_count ?: 0,
    width ?:0
)