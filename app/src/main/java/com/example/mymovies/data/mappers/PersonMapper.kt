package com.example.mymovies.data.mappers

import com.example.mymovies.data.remote.response.person_details.PersonDetailsDto
import com.example.mymovies.domain.model.person_details.PersonDetails

fun PersonDetailsDto.toPersonDetails() = PersonDetails(
    adult = adult ?: false,
    also_known_as = also_known_as ?: emptyList(),
    biography = biography ?: "",
    birthday = birthday ?: "",
    deathday = deathday ?: "",
    gender = gender ?: 0,
    homepage = homepage ?: "",
    id = id ?: -1,
    imdb_id = imdb_id ?: "",
    name = name ?: "",
    place_of_birth = place_of_birth ?: "",
    popularity = popularity ?: 0.0,
    profile_path = profile_path ?: "",
    known_for_department = known_for_department ?: ""
)