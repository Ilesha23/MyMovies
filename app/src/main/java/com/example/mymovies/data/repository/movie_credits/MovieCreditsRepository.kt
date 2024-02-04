package com.example.mymovies.data.repository.movie_credits

import com.example.mymovies.domain.model.movie_credits.Cast
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

}