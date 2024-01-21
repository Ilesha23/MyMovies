package com.example.mymovies.di

import android.app.Application
import androidx.room.Room
import com.example.mymovies.data.local.movie.MovieDatabase
import com.example.mymovies.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MovieApi.BASE_URL)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Application): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "moviedb"
        ).build()
    }

}