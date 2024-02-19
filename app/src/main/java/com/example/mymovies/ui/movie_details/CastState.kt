package com.example.mymovies.ui.movie_details

import com.example.mymovies.domain.model.movie_credits.Cast

data class CastState(
    val isLoading: Boolean = false,
    val cast: List<Cast> = emptyList(),
    val error: String? = null
)
