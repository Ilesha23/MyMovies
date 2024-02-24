package com.example.mymovies.data.repository.person.credits

import com.example.mymovies.data.mappers.toPersonCredits
import com.example.mymovies.data.remote.MovieApi
import com.example.mymovies.domain.model.person.credits.PersonCredits
import com.example.mymovies.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class PersonCreditsRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : PersonCreditsRepository {

    override suspend fun getPersonCredits(id: Int): Flow<Resource<PersonCredits>> {
        return flow<Resource<PersonCredits>> {
            val credits = withContext(Dispatchers.IO) {
                movieApi.getPersonCredits(id, Locale.getDefault().toLanguageTag())
            }.toPersonCredits()
            emit(Resource.Success(credits))
            return@flow
        }
            .onStart {
                emit(Resource.Loading(true))
            }
            .onCompletion {
                emit(Resource.Loading(false))
            }
            .catch {
                emit(Resource.Error(it.message))
                emit(Resource.Loading(false))
            }
    }

}