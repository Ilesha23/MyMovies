package com.example.mymovies.data.repository.movie.list

import com.example.mymovies.domain.model.movie.movie.Movie
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {

    suspend fun getPopularMovieList(
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getUpcomingMovies(
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getTopRatedMovies(
        page: Int
    ): Flow<Resource<List<Movie>>>

}