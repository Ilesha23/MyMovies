package com.example.mymovies.data.remote

import com.example.mymovies.data.remote.response.movie.movie.MovieListDto
import com.example.mymovies.data.remote.response.movie.credits.MovieCreditsDto
import com.example.mymovies.data.remote.response.movie.details.MovieDetailsDto
import com.example.mymovies.data.remote.response.movie.images.MovieImagesDto
import com.example.mymovies.data.remote.response.person.details.PersonDetailsDto
import com.example.mymovies.data.remote.response.person.images.PersonImagesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {



    @GET("movie/popular")
    suspend fun getPopularMovieList(
        @Query("page") page: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    // TODO: maybe refactor repeatable code
    // TODO: add date query
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
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

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("include_image_language") language: String = "en,null",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieImagesDto

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("include_image_language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieCreditsDto

    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): PersonDetailsDto

    @GET("person/{person_id}/images")
    suspend fun getPersonImages(
        @Path("person_id") id: Int,
        @Query("include_image_language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): PersonImagesDto



    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
        const val BACKDROP_BASE_URL = "https://media.themoviedb.org/t/p/w533_and_h300_bestv2/"
        const val API_KEY = "2fe37184b9686ecd6b16bf5fd449601f"
    }

}