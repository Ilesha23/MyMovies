package com.example.mymovies.data.mappers

import com.example.mymovies.data.remote.response.person_details.PersonDetailsDto
import com.example.mymovies.data.remote.response.person_images.PersonImagesDto
import com.example.mymovies.domain.model.person_details.PersonDetails

fun PersonDetailsDto.toPersonDetails() = PersonDetails(
    adult = adult ?: false,
    also_known_as = also_known_as ?: emptyList(),
    biography = biography.orEmpty(),
    birthday = birthday.orEmpty(),
    deathday = deathday.orEmpty(),
    gender = gender ?: 0,
    homepage = homepage.orEmpty(),
    id = id ?: -1,
    imdb_id = imdb_id.orEmpty(),
    name = name.orEmpty(),
    place_of_birth = place_of_birth.orEmpty(),
    popularity = popularity ?: 0.0,
    profile_path = profile_path.orEmpty(),
    known_for_department = known_for_department.orEmpty()
)

fun PersonImagesDto.toPersonImages() =
    profiles?.mapNotNull {
        it.file_path
    } ?: emptyList()