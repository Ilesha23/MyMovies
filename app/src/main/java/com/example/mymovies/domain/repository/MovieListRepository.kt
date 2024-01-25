package com.example.mymovies.domain.repository

import com.example.mymovies.domain.model.Movie
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {

    suspend fun getMoviesList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>>

}