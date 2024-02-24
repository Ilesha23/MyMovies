package com.example.mymovies.domain.usecases.person.credits

import com.example.mymovies.data.repository.person.credits.PersonCreditsRepositoryImpl
import com.example.mymovies.domain.model.person.credits.PersonCredits
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPersonCreditsUseCase @Inject constructor(
    private val personCreditsRepositoryImpl: PersonCreditsRepositoryImpl
) {

    suspend operator fun invoke(id: Int): Flow<Resource<PersonCredits>> {
        return personCreditsRepositoryImpl.getPersonCredits(id)
    }

}