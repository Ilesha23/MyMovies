package com.example.mymovies.data.repository.movie_list

import com.example.mymovies.data.local.movie.MovieDatabase
import com.example.mymovies.data.mappers.toMovie
import com.example.mymovies.data.mappers.toMovieEntity
import com.example.mymovies.data.remote.MovieApi
import com.example.mymovies.domain.model.movie.Movie
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDb: MovieDatabase
) : MovieListRepository {
    override suspend fun getPopularMovieList(
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteMovieList = try {
                movieApi.getPopularMovieList(page = page, language = Locale.getDefault().toLanguageTag())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message))
                return@flow
            }
            val movieEntityList = remoteMovieList.movies.map { it.toMovieEntity() } // TODO: remove
            emit(Resource.Success(
                movieEntityList.map {
                    it.toMovie()
                }
            ))
            emit(Resource.Loading(false))
            return@flow
        }
    }

    // TODO: refactor repeatable code
    override suspend fun getUpcomingMovies(page: Int): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())

            val remoteUpcomingMovieList = try {
                movieApi.getUpcomingMovies(page, Locale.getDefault().toLanguageTag())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message)) // TODO:
                emit(Resource.Loading(false))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message))
                return@flow
            }
            val movieEntityList = remoteUpcomingMovieList.movies.map { it.toMovieEntity() }
            emit(Resource.Success(
                movieEntityList.map {
                    it.toMovie()
                }
            ))
            emit(Resource.Loading(false))
            return@flow
        }
    }

    override suspend fun getTopRatedMovies(page: Int): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())

            val remoteTopRatedMovieList = try {
                movieApi.getTopRatedMovies(page, Locale.getDefault().toLanguageTag())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message)) // TODO:
                emit(Resource.Loading(false))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message))
                return@flow
            }
            val movieEntityList = remoteTopRatedMovieList.movies.map { it.toMovieEntity() }
            emit(Resource.Success(
                movieEntityList.map {
                    it.toMovie()
                }
            ))
            emit(Resource.Loading(false))
            return@flow
        }
    }

}