package com.example.mymovies.data.repository.movie_details

import com.example.mymovies.data.mappers.toMovieDetails
import com.example.mymovies.data.remote.MovieApi
import com.example.mymovies.domain.model.movie_details.MovieDetails
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieDetailsRepository {

    override suspend fun getDetails(id: Int): Flow<Resource<MovieDetails>> {
        return flow {
            emit(Resource.Loading(true))

            try {
                val remoteDetails = movieApi.getMovieDetails(id, Locale.getDefault().toLanguageTag())
                val movieDetails = remoteDetails.toMovieDetails()
                emit(Resource.Success(movieDetails))
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

}