package com.example.mymovies.data.repository.movie_images

import com.example.mymovies.domain.model.movie_images.MovieImages
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieImagesRepository {

    suspend fun getMovieImages(
        id: Int
    ): Flow<Resource<MovieImages>>

}