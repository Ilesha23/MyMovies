package com.example.mymovies.data.mappers

import com.example.mymovies.data.remote.response.movie_credits.CastDto
import com.example.mymovies.data.remote.response.movie_credits.CrewDto
import com.example.mymovies.data.remote.response.movie_credits.MovieCreditsDto
import com.example.mymovies.domain.model.movie_credits.Cast
import com.example.mymovies.domain.model.movie_credits.Crew
import com.example.mymovies.domain.model.movie_credits.MovieCredits

fun MovieCreditsDto.toMovieCredits() = MovieCredits(
    cast?.map { it.toCast() } ?: emptyList(),
    crew?.map { it.toCrew() } ?: emptyList(),
    id ?: -1
)

fun CastDto.toCast() = Cast(
    adult ?: false,
    cast_id ?: -1,
    character ?: "",
    credit_id ?: "",
    gender ?: 1,
    id ?: -1,
    known_for_department ?: "",
    name ?: "",
    order ?: -1,
    original_name ?: "",
    popularity ?: 0.0,
    profile_path ?: ""
)

fun CrewDto.toCrew() = Crew(
    adult ?: false,
    credit_id ?: "",
    department ?: "",
    gender ?: 1,
    id ?: -1,
    job ?: "",
    known_for_department ?: "",
    name ?: "",
    original_name ?: "",
    popularity ?: 0.0,
    profile_path ?: ""
)