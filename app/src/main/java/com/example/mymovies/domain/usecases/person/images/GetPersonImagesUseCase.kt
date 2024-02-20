package com.example.mymovies.domain.usecases.person.images

import com.example.mymovies.data.repository.person.images.PersonImagesRepositoryImpl
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPersonImagesUseCase @Inject constructor(
    private val personImagesRepositoryImpl: PersonImagesRepositoryImpl
) {

    suspend operator fun invoke(id: Int): Flow<Resource<List<String>>> {
        return personImagesRepositoryImpl.getPersonImages(id)
    }

}