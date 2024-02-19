package com.example.mymovies.domain.usecases.movie_list

import com.example.mymovies.data.repository.movie_list.MovieListRepositoryImpl
import com.example.mymovies.domain.model.movie.Movie
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMovieListUseCase @Inject constructor(
    private val movieListRepositoryImpl: MovieListRepositoryImpl
) {

    suspend operator fun invoke(page: Int): Flow<Resource<List<Movie>>> {
        return movieListRepositoryImpl.getPopularMovieList(page)
    }

}