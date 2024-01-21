package com.example.mymovies.data.mappers

import com.example.mymovies.data.local.movie.MovieEntity
import com.example.mymovies.data.remote.response.MovieDto
import com.example.mymovies.domain.model.Movie
import java.lang.Exception

fun MovieDto.toMovieEntity() = MovieEntity(
    adult = adult ?: false,
    backdrop_path = backdrop_path ?: "",
    genre_ids = try {
        genre_ids?.joinToString(",")
    } catch (e: Exception) {
        "-1,-2"
    } ?: "-1,-2",
    id = id ?: -1,
    original_language = original_language ?: "",
    original_title = original_title ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    poster_path = poster_path ?: "",
    release_date = release_date ?: "",
    title = title ?: "",
    video = video ?: false,
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: 0
)

fun MovieEntity.toMovie() = Movie(
    adult = adult,
    backdrop_path = backdrop_path,
    genre_ids = try {
        genre_ids.split(",").map { it.toInt() }
    } catch (e: Exception) {
        listOf(1, 2)
    },
    id = id,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)