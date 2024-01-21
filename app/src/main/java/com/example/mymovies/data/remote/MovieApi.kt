package com.example.mymovies.data.remote

import com.example.mymovies.data.remote.response.MovieListDto
import com.example.mymovies.util.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {



    @GET("movie/popular")
    suspend fun getMovieList(
        @Path("page") page: Int,
        @Path("api_key") apiKey: String = API_KEY
    ): MovieListDto



    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
        const val API_KEY = "2fe37184b9686ecd6b16bf5fd449601f"
    }

}