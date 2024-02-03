package com.example.mymovies.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.R
import com.example.mymovies.data.repository.MovieListRepository
import com.example.mymovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    private val _movieViewState = MutableStateFlow(MovieListViewState())
    val movieViewState = _movieViewState.asStateFlow()

    init {
        getMovieList(true, true) // TODO:
    }

    fun onEvent(event: MovieListUiEvent) {
        when (event) {
            MovieListUiEvent.Paginate -> {
                if (_movieViewState.value.popularFilter) getMovieList(true, true)
                if (_movieViewState.value.upcomingFilter) getUpcomingMovies(true)
//                if (_movieViewState.value.topRatedFilter) getMovieList(true)
            }
        }
    }

    private fun getMovieList(forceFetchFromRemote: Boolean, shouldAddToList: Boolean) {
        if (_movieListState.value.isLoading)
            return
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            movieListRepository.getMoviesList(
                forceFetchFromRemote,
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
//                                isLoading = false,
                                error = R.string.no_internet
                            )
                        }
//                        delay(3000)
//                        getMovieList(true) // TODO: just repeat few times
                    }

                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(
                                isLoading = /*true,*/result.isLoading,
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

            movieListRepository.getUpcomingMovies(
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
//                                isLoading = false,
                                error = R.string.no_internet
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(
                                isLoading = /*true,*/result.isLoading,
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
        getMovieList(true, false)
    }

    fun clickUpcomingFilter() {
        if (!_movieViewState.value.upcomingFilter) _movieListState.update { it.copy(page = 1) } else return
        _movieViewState.update { it.copy(popularFilter = false, upcomingFilter = true, topRatedFilter = false) }
        getUpcomingMovies(false)
    }

    fun clickTopRatedFilter() {
        if (!_movieViewState.value.topRatedFilter) _movieListState.update { it.copy(page = 1) } else return
        _movieViewState.update { it.copy(popularFilter = false, upcomingFilter = false, topRatedFilter = true) }
    }

}