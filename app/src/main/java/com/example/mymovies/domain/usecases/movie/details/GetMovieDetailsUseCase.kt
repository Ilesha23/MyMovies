package com.example.mymovies.domain.usecases.movie.details

import com.example.mymovies.data.repository.movie.details.MovieDetailsRepositoryImpl
import com.example.mymovies.domain.model.movie.details.MovieDetails
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl
) {

    suspend operator fun invoke(id: Int): Flow<Resource<MovieDetails>> {
        return movieDetailsRepositoryImpl.getDetails(id)
    }

}