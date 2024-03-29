package com.example.mymovies.domain.usecases.movie.list

import com.example.mymovies.data.repository.movie.list.MovieListRepositoryImpl
import com.example.mymovies.domain.model.movie.movie.Movie
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMovieListUseCase @Inject constructor(
    private val movieListRepositoryImpl: MovieListRepositoryImpl
) {

    suspend operator fun invoke(page: Int): Flow<Resource<List<Movie>>> {
        return movieListRepositoryImpl.getTopRatedMovies(page)
    }

}