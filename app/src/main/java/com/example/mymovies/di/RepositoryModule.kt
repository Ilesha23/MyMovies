package com.example.mymovies.di

import com.example.mymovies.data.repository.movie.credits.MovieCreditsRepository
import com.example.mymovies.data.repository.movie.credits.MovieCreditsRepositoryImpl
import com.example.mymovies.data.repository.movie.details.MovieDetailsRepositoryImpl
import com.example.mymovies.data.repository.movie.images.MovieImagesRepositoryImpl
import com.example.mymovies.data.repository.movie.list.MovieListRepositoryImpl
import com.example.mymovies.data.repository.movie.details.MovieDetailsRepository
import com.example.mymovies.data.repository.movie.images.MovieImagesRepository
import com.example.mymovies.data.repository.movie.list.MovieListRepository
import com.example.mymovies.data.repository.person.details.PersonDetailsRepository
import com.example.mymovies.data.repository.person.details.PersonDetailsRepositoryImpl
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