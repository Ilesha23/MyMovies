package com.example.mymovies.data.repository.person.details

import com.example.mymovies.data.mappers.toPersonDetails
import com.example.mymovies.data.remote.MovieApi
import com.example.mymovies.domain.model.person_details.PersonDetails
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class PersonDetailsRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : PersonDetailsRepository {

    override suspend fun getPersonDetails(id: Int): Flow<Resource<PersonDetails>> {
        return flow {
            emit(Resource.Loading(true))

            try {
                val detailsDto = movieApi.getPersonDetails(id, Locale.getDefault().toLanguageTag())
                val details = detailsDto.toPersonDetails()
                if (details.biography.isBlank()) {
                    val bio = movieApi.getPersonDetails(id).biography
                    val detailsWithBio = details.copy(biography = "(no info provided for your language) $bio")
                    emit(Resource.Success(detailsWithBio))
                } else {
                    emit(Resource.Success(details))
                }
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