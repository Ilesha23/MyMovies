package com.example.mymovies.di

import com.example.mymovies.data.repository.MovieDetailsRepositoryImpl
import com.example.mymovies.data.repository.MovieImagesRepositoryImpl
import com.example.mymovies.data.repository.MovieListRepositoryImpl
import com.example.mymovies.data.repository.MovieDetailsRepository
import com.example.mymovies.data.repository.MovieImagesRepository
import com.example.mymovies.data.repository.MovieListRepository
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

}