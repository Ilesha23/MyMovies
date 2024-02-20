package com.example.mymovies.data.repository.person.images

import com.example.mymovies.data.mappers.toPersonImages
import com.example.mymovies.data.remote.MovieApi
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class PersonImagesRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : PersonImagesRepository {

    override suspend fun getPersonImages(id: Int): Flow<Resource<List<String>>> {
        return flow {
            try {
                val imagesDto = movieApi.getPersonImages(id, Locale.getDefault().toLanguageTag())
                val images = imagesDto.toPersonImages()
                emit(Resource.Success(images))
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