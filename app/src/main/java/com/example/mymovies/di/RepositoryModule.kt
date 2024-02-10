package com.example.mymovies.di

import com.example.mymovies.data.repository.movie_credits.MovieCreditsRepository
import com.example.mymovies.data.repository.movie_credits.MovieCreditsRepositoryImpl
import com.example.mymovies.data.repository.movie_details.MovieDetailsRepositoryImpl
import com.example.mymovies.data.repository.movie_images.MovieImagesRepositoryImpl
import com.example.mymovies.data.repository.movie_list.MovieListRepositoryImpl
import com.example.mymovies.data.repository.movie_details.MovieDetailsRepository
import com.example.mymovies.data.repository.movie_images.MovieImagesRepository
import com.example.mymovies.data.repository.movie_list.MovieListRepository
import com.example.mymovies.data.repository.person.PersonDetailsRepository
import com.example.mymovies.data.repository.person.PersonDetailsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(movieListRepositoryImpl: MovieListRepositoryImpl): MovieListRepository

    @Binds
    @Singleton
    abstract fun bindMovieDetailsRepository(movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl): MovieDetailsRepository

    @Binds
    @Singleton
    abstract fun bindMovieImagesRepository(movieImagesRepositoryImpl: MovieImagesRepositoryImpl): MovieImagesRepository

    @Binds
    @Singleton
    abstract fun bindMovieCreditsRepository(movieCreditsRepositoryImpl: MovieCreditsRepositoryImpl): MovieCreditsRepository

    @Binds
    @Singleton
    abstract fun bindPersonDetailsRepository(personDetailsRepositoryImpl: PersonDetailsRepositoryImpl): PersonDetailsRepository

}