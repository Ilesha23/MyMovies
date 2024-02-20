package com.example.mymovies.data.repository.movie.images

import com.example.mymovies.data.mappers.toMovieImages
import com.example.mymovies.data.remote.MovieApi
import com.example.mymovies.domain.model.movie.images.MovieImages
import com.example.mymovies.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class MovieImagesRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieImagesRepository {

    override suspend fun getMovieImages(id: Int): Flow<Resource<MovieImages>> {
        return flow {

            emit(Resource.Loading(true))

            try {
                val movieImages = withContext(Dispatchers.IO) {
                    movieApi
                        .getMovieImages(id, Locale.getDefault().language + ",en,null")
                        .toMovieImages()
                }
                emit(Resource.Success(movieImages))
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