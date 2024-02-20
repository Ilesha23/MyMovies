package com.example.mymovies.domain.usecases.movie.images

import com.example.mymovies.data.repository.movie.images.MovieImagesRepositoryImpl
import com.example.mymovies.domain.model.movie.images.MovieImages
import com.example.mymovies.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieImagesUseCase @Inject constructor(
    private val movieImagesRepositoryImpl: MovieImagesRepositoryImpl
) {

    suspend operator fun invoke(id: Int): Flow<Resource<MovieImages>> {
        return movieImagesRepositoryImpl.getMovieImages(id)
    }

}