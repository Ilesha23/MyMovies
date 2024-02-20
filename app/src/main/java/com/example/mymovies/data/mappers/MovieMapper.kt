package com.example.mymovies.data.mappers

import com.example.mymovies.data.local.movie.MovieEntity
import com.example.mymovies.data.remote.response.movie.movie.MovieDto
import com.example.mymovies.data.remote.response.movie.details.BelongsToCollectionDto
import com.example.mymovies.data.remote.response.movie.details.GenreDto
import com.example.mymovies.data.remote.response.movie.details.MovieDetailsDto
import com.example.mymovies.data.remote.response.movie.details.ProductionCompanyDto
import com.example.mymovies.data.remote.response.movie.details.ProductionCountryDto
import com.example.mymovies.data.remote.response.movie.details.SpokenLanguageDto
import com.example.mymovies.domain.model.movie.movie.Movie
import com.example.mymovies.domain.model.movie.details.BelongsToCollection
import com.example.mymovies.domain.model.movie.details.Genre
import com.example.mymovies.domain.model.movie.details.MovieDetails
import com.example.mymovies.domain.model.movie.details.ProductionCompany
import com.example.mymovies.domain.model.movie.details.ProductionCountry
import com.example.mymovies.domain.model.movie.details.SpokenLanguage

fun MovieDto.toMovieEntity() = MovieEntity(
    adult = adult ?: false,
    backdrop_path = backdrop_path.orEmpty(),
    genre_ids = try {
        genre_ids?.joinToString(",")
    } catch (e: Exception) {
        "-1,-2"
    } ?: "-1,-2",
    id = id ?: -1,
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

fun MovieDetailsDto.toMovieDetails() = MovieDetails(
    adult = adult ?: false,
    backdrop_path = backdrop_path.orEmpty(),
    belongs_to_collection = belongs_to_collection?.toBelongsToCollection()
        ?: BelongsToCollection("", 0, "", ""),
    budget = budget ?: 0,
    genres = genres?.map { it.toGenre() } ?: emptyList(),
    homepage = homepage.orEmpty(),
    id = id ?: 0,
    imdb_id = imdb_id.orEmpty(),
    original_language = original_language.orEmpty(),
    original_title = original_title.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    poster_path = poster_path.orEmpty(),
    production_companies = production_companies?.map { it.toProductionCompany() } ?: emptyList(),
    production_countries = production_countries?.map { it.toProductionCountry() } ?: emptyList(),
    release_date = release_date.orEmpty(),
    revenue = revenue ?: 0,
    runtime = runtime ?: 0,
    spoken_languages = spoken_languages?.map { it.toSpokenLanguage() } ?: emptyList(),
    status = status.orEmpty(),
    tagline = tagline.orEmpty(),
    title = title.orEmpty(),
    video = video ?: false,
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: 0
)

fun BelongsToCollectionDto.toBelongsToCollection() = BelongsToCollection(
    backdrop_path = backdrop_path.orEmpty(),
    id = id ?: 0,
    name = name.orEmpty(),
    poster_path = poster_path.orEmpty()
)

fun GenreDto.toGenre() = Genre(
    id = id ?: 0,
    name = name.orEmpty()
)

fun ProductionCompanyDto.toProductionCompany() = ProductionCompany(
    id = id ?: 0,
    logo_path = logo_path.orEmpty(),
    name = name.orEmpty(),
    origin_country = origin_country.orEmpty()
)

fun ProductionCountryDto.toProductionCountry() = ProductionCountry(
    iso_3166_1 = iso_3166_1.orEmpty(),
    name = name.orEmpty()
)

fun SpokenLanguageDto.toSpokenLanguage() = SpokenLanguage(
    english_name = english_name.orEmpty(),
    iso_639_1 = iso_639_1.orEmpty(),
    name = name.orEmpty()
)
