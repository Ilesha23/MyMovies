package com.example.mymovies.domain.usecases.movie_credits

import com.example.mymovies.data.repository.movie_credits.MovieCreditsRepositoryImpl
import com.example.mymovies.domain.model.movie_credits.Crew
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieCrewUseCase @Inject constructor(
    private val movieCreditsRepositoryImpl: MovieCreditsRepositoryImpl
) {

    suspend operator fun invoke(id: Int): Flow<Resource<List<Crew>>> {
        return movieCreditsRepositoryImpl.getCrew(id)
    }

}