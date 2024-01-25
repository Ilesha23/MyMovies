package com.example.mymovies.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.R
import com.example.mymovies.domain.repository.MovieListRepository
import com.example.mymovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel() {

    private val _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getMovieList(true) // TODO:
    }

    fun onEvent(event: MovieListUiEvent) {
        when(event) {
            MovieListUiEvent.Paginate -> {
                getMovieList(true)
            }
        }
    }

    private fun getMovieList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            movieListRepository.getMoviesList(
                forceFetchFromRemote,
                _movieListState.value.page
            ).collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let { list ->
                            _movieListState.update {
                                it.copy(
                                    list = movieListState.value.list + list,
                                    page = _movieListState.value.page + 1,
                                    error = null
                                )
                            }
                        }
                    }
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(
                                isLoading = false,
                                error = R.string.no_internet
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(
                                isLoading = result.isLoading,
                                error = null
                            )
                        }
                    }
                }
            }
        }
    }

}