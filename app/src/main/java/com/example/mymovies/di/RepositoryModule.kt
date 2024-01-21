package com.example.mymovies.di

import com.example.mymovies.data.repository.MovieListRepositoryImpl
import com.example.mymovies.domain.repository.MovieListRepository
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

}