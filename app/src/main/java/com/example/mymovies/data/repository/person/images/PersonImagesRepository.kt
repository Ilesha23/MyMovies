package com.example.mymovies.data.repository.person.images

import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface PersonImagesRepository {

    suspend fun getPersonImages(
        id: Int
    ): Flow<Resource<List<String>>>

}