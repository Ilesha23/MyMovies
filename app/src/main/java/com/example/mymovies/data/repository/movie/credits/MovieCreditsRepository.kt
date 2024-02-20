package com.example.mymovies.data.repository.movie.credits

import com.example.mymovies.domain.model.movie_credits.Cast
import com.example.mymovies.domain.model.movie_credits.Crew
import com.example.mymovies.domain.model.movie_credits.MovieCredits
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieCreditsRepository {

    suspend fun getCredits(
        id: Int
    ): Flow<Resource<MovieCredits>>

    suspend fun getCast(
        id: Int
    ): Flow<Resource<List<Cast>>>

    suspend fun getCrew(
        id: Int
    ): Flow<Resource<List<Crew>>>

}