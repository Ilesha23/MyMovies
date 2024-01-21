package com.example.mymovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieListDto(
    val page: Int,
    @SerializedName("results") val movies: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)