package com.example.mymovies.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.R
import com.example.mymovies.domain.usecases.movie_list.GetPopularMovieListUseCase
import com.example.mymovies.domain.usecases.movie_list.GetTopRatedMovieListUseCase
import com.example.mymovies.domain.usecases.movie_list.GetUpcomingMovieListUseCase
import com.example.mymovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase,
    private val getTopRatedMovieListUseCase: GetTopRatedMovieListUseCase,
) : ViewModel() {

    private val _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    private val _movieViewState = MutableStateFlow(MovieListViewState())
    val movieViewState = _movieViewState.asStateFlow()

    init {
        getMovieList(true) // TODO:
    }

    fun onEvent(event: MovieListUiEvent) {
        when (event) {
            MovieListUiEvent.Paginate -> {
                if (_movieViewState.value.popularFilter) getMovieList(true)
                if (_movieViewState.value.upcomingFilter) getUpcomingMovies(true)
                if (_movieViewState.value.topRatedFilter) getTopRatedMovies(true)
            }
        }
    }

    private fun getMovieList(shouldAddToList: Boolean) {
        if (_movieListState.value.isLoading)
            return
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            getPopularMovieListUseCase(
                _movieListState.value.page
            ).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { list ->
                            val uniqueItems = list.filter { newItem ->
                                _movieListState.value.list.none { it.title == newItem.title }
                            }
                            _movieListState.update {
                                it.copy(
                                    list = if (shouldAddToList) movieListState.value.list + uniqueItems else uniqueItems,
                                    page = if (shouldAddToList) _movieListState.value.page + 1 else _movieListState.value.page,
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
                                isLoading = true,
                                error = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getUpcomingMovies(shouldAddToList: Boolean) {
        if (_movieListState.value.isLoading)
            return

        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            getUpcomingMovieListUseCase(
                _movieListState.value.page
            ).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { list ->
                            val uniqueItems = list.filter { newItem ->
                                _movieListState.value.list.none { it.title == newItem.title }
                            }
                            _movieListState.update {
                                it.copy(
                                    list = if (shouldAddToList) movieListState.value.list + uniqueItems else uniqueItems,
                                    page = if (shouldAddToList) _movieListState.value.page + 1 else _movieListState.value.page,
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
                                isLoading = true,
                                error = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getTopRatedMovies(shouldAddToList: Boolean) {
        if (_movieListState.value.isLoading)
            return

        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            getTopRatedMovieListUseCase(
                _movieListState.value.page
            ).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { list ->
                            val uniqueItems = list.filter { newItem ->
                                _movieListState.value.list.none { it.title == newItem.title }
                            }
                            _movieListState.update {
                                it.copy(
                                    list = if (shouldAddToList) movieListState.value.list + uniqueItems else uniqueItems,
                                    page = if (shouldAddToList) _movieListState.value.page + 1 else _movieListState.value.page,
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
                                isLoading = true,
                                error = null
                            )
                        }
                    }
                }
            }
        }
    }

    fun clickPopularFilter() {
        if (!_movieViewState.value.popularFilter) _movieListState.update { it.copy(page = 1) } else return
        _movieViewState.update { it.copy(popularFilter = true, upcomingFilter = false, topRatedFilter = false) }
        getMovieList(false)
    }

    fun clickUpcomingFilter() {
        if (!_movieViewState.value.upcomingFilter) _movieListState.update { it.copy(page = 1) } else return
        _movieViewState.update { it.copy(popularFilter = false, upcomingFilter = true, topRatedFilter = false) }
        getUpcomingMovies(false)
    }

    fun clickTopRatedFilter() {
        if (!_movieViewState.value.topRatedFilter) _movieListState.update { it.copy(page = 1) } else return
        _movieViewState.update { it.copy(popularFilter = false, upcomingFilter = false, topRatedFilter = true) }
        getTopRatedMovies(false)
    }

}