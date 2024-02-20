package com.example.mymovies.data.repository.movie.details

import com.example.mymovies.domain.model.movie_details.MovieDetails
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    suspend fun getDetails(
        id: Int
    ): Flow<Resource<MovieDetails>>

}