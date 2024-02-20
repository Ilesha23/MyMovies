package com.example.mymovies.data.repository.movie.credits

import com.example.mymovies.data.mappers.toMovieCredits
import com.example.mymovies.data.remote.MovieApi
import com.example.mymovies.domain.model.movie_credits.Cast
import com.example.mymovies.domain.model.movie_credits.Crew
import com.example.mymovies.domain.model.movie_credits.MovieCredits
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class MovieCreditsRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieCreditsRepository {

    override suspend fun getCredits(id: Int): Flow<Resource<MovieCredits>> {
        return flow {
            emit(Resource.Loading(true))

            try {
                val remoteCredits = movieApi.getMovieCredits(id, Locale.getDefault().toLanguageTag())
                val movieCredits = remoteCredits.toMovieCredits()
                emit(Resource.Success(movieCredits))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e.message))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.message))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message))
                emit(Resource.Loading(false))
                return@flow
            }

        }
    }

    override suspend fun getCast(id: Int): Flow<Resource<List<Cast>>> {
        return getCredits(id).map { resource ->
            when(resource) {
                is Resource.Success -> {
                    val castList = resource.data?.cast ?: emptyList()
                    Resource.Success(castList)
                }
                is Resource.Error -> {
                    Resource.Error(resource.message)
                }
                is Resource.Loading -> {
                    Resource.Loading(resource.isLoading)
                }
            }
        }
    }

    override suspend fun getCrew(id: Int): Flow<Resource<List<Crew>>> {
        return getCredits(id).map { resource ->
            when(resource) {
                is Resource.Success -> {
                    val crewList = resource.data?.crew ?: emptyList()
                    Resource.Success(crewList)
                }
                is Resource.Error -> {
                    Resource.Error(resource.message)
                }
                is Resource.Loading -> {
                    Resource.Loading(resource.isLoading)
                }
            }
        }
    }

}