package com.example.mymovies.ui.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.domain.usecases.movie_credits.GetMovieCastUseCase
import com.example.mymovies.domain.usecases.movie_credits.GetMovieCrewUseCase
import com.example.mymovies.domain.usecases.movie_details.GetMovieDetailsUseCase
import com.example.mymovies.domain.usecases.movie_images.GetMovieImagesUseCase
import com.example.mymovies.ui.movie_details.state.CastState
import com.example.mymovies.ui.movie_details.state.CrewState
import com.example.mymovies.ui.movie_details.state.DetailsState
import com.example.mymovies.ui.movie_details.state.ImagesState
import com.example.mymovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getMovieCrewUseCase: GetMovieCrewUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId = savedStateHandle.get<Int>("movieId")

    private val _detailsState = MutableStateFlow(DetailsState(details = null))
    val detailsState = _detailsState.asStateFlow()

    private val _imagesState = MutableStateFlow(ImagesState())
    val imagesState = _imagesState.asStateFlow()

    private val _castState = MutableStateFlow(CastState())
    val castState = _castState.asStateFlow()

    private val _crewState = MutableStateFlow(CrewState())
    val crewState = _crewState.asStateFlow()

    init {
        getDetails(movieId ?: -1)
        getMovieImages(movieId ?: -1)
        getCast(movieId ?: -1)
        getCrew(movieId ?: -1)
    }

    private fun getDetails(movieId: Int) {
        viewModelScope.launch {

            _detailsState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getMovieDetailsUseCase(movieId)
                .collectLatest {
                    when (it) {
                        is Resource.Success -> {
                            _detailsState.update { state ->
                                state.copy(
                                    details = it.data?.copy(),
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            _detailsState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    error = it.message // todo get string resource
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _detailsState.update { state ->
                                state.copy(
                                    isLoading = true,
                                    error = null
                                )
                            }
                        }
                    }
                }

        }
    }

    fun convertCurrencyToString(value: Int): String {
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)
        (currencyFormat as java.text.DecimalFormat).apply {
            minimumFractionDigits = 0
            maximumFractionDigits = 0
        }
        return currencyFormat.format(value.toLong())
    }

    private fun getMovieImages(movieId: Int) {
        viewModelScope.launch {
            _imagesState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getMovieImagesUseCase(movieId)
                .collectLatest {
                    when (it) {
                        is Resource.Success -> {
                            val backdrops = it.data?.backdrops?.map { backdrop ->
                                backdrop.file_path
                            } ?: emptyList()
                            _imagesState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    images = backdrops,
                                    error = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            _imagesState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    error = it.message
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _imagesState.update { state ->
                                state.copy(
                                    isLoading = true,
                                    error = null
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun getCast(movieId: Int) {
        viewModelScope.launch {
            _castState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getMovieCastUseCase(movieId)
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _castState.update {
                                it.copy(
                                    isLoading = false,
                                    cast = resource.data ?: emptyList(),
                                    error = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            _castState.update {
                                it.copy(
                                    isLoading = false,
                                    error = resource.message
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _castState.update {
                                it.copy(
                                    isLoading = true,
                                    error = null
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun getCrew(movieId: Int) {
        viewModelScope.launch {
            _crewState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getMovieCrewUseCase(movieId)
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _crewState.update {
                                it.copy(
                                    isLoading = false,
                                    crew = resource.data ?: emptyList(),
                                    error = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            _crewState.update {
                                it.copy(
                                    isLoading = false,
                                    error = resource.message
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _crewState.update {
                                it.copy(
                                    isLoading = true,
                                    error = null
                                )
                            }
                        }
                    }
                }
        }
    }


}