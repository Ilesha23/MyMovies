package com.example.mymovies.data.mappers

import com.example.mymovies.data.remote.response.movie.credits.CastDto
import com.example.mymovies.data.remote.response.movie.credits.CrewDto
import com.example.mymovies.data.remote.response.movie.credits.MovieCreditsDto
import com.example.mymovies.data.remote.response.person.credits.PersonCastDto
import com.example.mymovies.data.remote.response.person.credits.PersonCreditsDto
import com.example.mymovies.data.remote.response.person.credits.PersonCrewDto
import com.example.mymovies.domain.model.movie.credits.Cast
import com.example.mymovies.domain.model.movie.credits.Crew
import com.example.mymovies.domain.model.movie.credits.MovieCredits
import com.example.mymovies.domain.model.person.credits.PersonCast
import com.example.mymovies.domain.model.person.credits.PersonCredits
import com.example.mymovies.domain.model.person.credits.PersonCrew

fun MovieCreditsDto.toMovieCredits() = MovieCredits(
    cast?.map { it.toCast() } ?: emptyList(),
    crew?.map { it.toCrew() } ?: emptyList(),
    id ?: -1
)

fun CastDto.toCast() = Cast(
    adult ?: false,
    cast_id ?: -1,
    character.orEmpty(),
    credit_id.orEmpty(),
    gender ?: 1,
    id ?: -1,
    known_for_department.orEmpty(),
    name.orEmpty(),
    order ?: -1,
    original_name.orEmpty(),
    popularity ?: 0.0,
    profile_path.orEmpty()
)

fun CrewDto.toCrew() = Crew(
    adult ?: false,
    credit_id.orEmpty(),
    department.orEmpty(),
    gender ?: 1,
    id ?: -1,
    job.orEmpty(),
    known_for_department.orEmpty(),
    name.orEmpty(),
    original_name.orEmpty(),
    popularity ?: 0.0,
    profile_path.orEmpty()
)


fun PersonCreditsDto.toPersonCredits(): PersonCredits {
    return PersonCredits(
        cast = cast?.map { it.toPersonCast() } ?: emptyList(),
        crew = crew?.map { it.toPersonCrew() } ?: emptyList(),
        id = id ?: -1
    )
}

fun PersonCastDto.toPersonCast() = PersonCast(
    adult = adult ?: false,
    backdrop_path = backdrop_path.orEmpty(),
    character = character.orEmpty(),
    credit_id = credit_id.orEmpty(),
    genre_ids = genre_ids ?: emptyList(),
    id = id ?: -1,
    order = order ?: -1,
    original_language = original_language.orEmpty(),
    original_title = original_title.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    poster_path = poster_path.orEmpty(),
    release_date = release_date.orEmpty(),
    title = title.orEmpty(),
    video = video ?: false,
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: 0
)

fun PersonCrewDto.toPersonCrew() = PersonCrew(
    adult = adult ?: false,
    backdrop_path = backdrop_path.orEmpty(),
    credit_id = credit_id.orEmpty(),
    department = department.orEmpty(),
    genre_ids = genre_ids ?: emptyList(),
    id = id ?: -1,
    job = job.orEmpty(),
    original_language = original_language.orEmpty(),
    original_title = original_title.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    poster_path = poster_path.orEmpty(),
    release_date = release_date.orEmpty(),
    title = title.orEmpty(),
    video = video ?: false,
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: 0
)
