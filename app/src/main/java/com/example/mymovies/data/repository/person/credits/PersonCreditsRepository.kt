package com.example.mymovies.data.repository.person.credits

import com.example.mymovies.domain.model.person.credits.PersonCredits
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface PersonCreditsRepository {

    suspend fun getPersonCredits(
        id: Int
    ): Flow<Resource<PersonCredits>>

}