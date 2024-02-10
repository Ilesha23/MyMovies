package com.example.mymovies.data.repository.person

import com.example.mymovies.domain.model.person_details.PersonDetails
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface PersonDetailsRepository {

    suspend fun getPersonDetails(
        id: Int
    ): Flow<Resource<PersonDetails>>

}