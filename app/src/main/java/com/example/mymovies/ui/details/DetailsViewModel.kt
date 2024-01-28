package com.example.mymovies.ui.details

import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.data.repository.MovieDetailsRepositoryImpl
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
class DetailsViewModel @Inject constructor(
    private val movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId = savedStateHandle.get<Int>("movieId")

    private val _detailsState = MutableStateFlow(DetailsState(details = null))
    val detailsState = _detailsState.asStateFlow()

    init {
        getDetails(movieId ?: -1)
    }

    fun getDetails(movieId: Int) {
        viewModelScope.launch {

            _detailsState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            movieDetailsRepositoryImpl.getDetails(movieId)
                .collectLatest {
                    when(it) {
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

}