package com.example.mymovies.data.remote

import com.example.mymovies.data.remote.response.movie.MovieListDto
import com.example.mymovies.data.remote.response.movie_details.MovieDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {



    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("page") page: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDetailsDto



    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
        const val BACKDROP_BASE_URL = "https://media.themoviedb.org/t/p/w533_and_h300_bestv2/"
        const val API_KEY = "2fe37184b9686ecd6b16bf5fd449601f"
    }

}