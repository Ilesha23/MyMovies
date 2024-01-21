package com.example.mymovies.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovieList(list: List<MovieEntity>)

    @Query("SELECT * FROM movie")
    suspend fun getMoviesList(): List<MovieEntity>

}