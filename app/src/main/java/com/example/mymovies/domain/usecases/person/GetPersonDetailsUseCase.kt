package com.example.mymovies.domain.usecases.person

import com.example.mymovies.data.repository.person.details.PersonDetailsRepositoryImpl
import com.example.mymovies.domain.model.person_details.PersonDetails
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(
    private val personDetailsRepositoryImpl: PersonDetailsRepositoryImpl
) {

    suspend operator fun invoke(id: Int): Flow<Resource<PersonDetails>> {
        return personDetailsRepositoryImpl.getPersonDetails(id)
    }

}