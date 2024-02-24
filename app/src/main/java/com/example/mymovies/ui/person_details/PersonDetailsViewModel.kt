package com.example.mymovies.ui.person_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.domain.usecases.person.credits.GetPersonCreditsUseCase
import com.example.mymovies.domain.usecases.person.details.GetPersonDetailsUseCase
import com.example.mymovies.domain.usecases.person.images.GetPersonImagesUseCase
import com.example.mymovies.ui.person_details.state.PersonCreditsState
import com.example.mymovies.ui.person_details.state.PersonDetailsState
import com.example.mymovies.ui.person_details.state.PersonImagesState
import com.example.mymovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase,
    private val getPersonImagesUseCase: GetPersonImagesUseCase,
    private val getPersonCreditsUseCase: GetPersonCreditsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val personId = savedStateHandle.get<Int>("personId")

    private val _detailsState = MutableStateFlow(PersonDetailsState(details = null))
    val detailsState = _detailsState.asStateFlow()

    private val _imagesState = MutableStateFlow(PersonImagesState())
    val imagesState = _imagesState.asStateFlow()

    private val _creditsState = MutableStateFlow(PersonCreditsState())
    val creditsState = _creditsState.asStateFlow()

    init {
        getDetails(personId ?: -1)
        getImages(personId ?: -1)
        getPersonCredits(personId ?: -1)
    }

    private fun getDetails(id: Int) {

        viewModelScope.launch {
            _detailsState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getPersonDetailsUseCase(id)
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _detailsState.update {
                                it.copy(
                                    isLoading = false,
                                    details = resource.data,
                                    error = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            _detailsState.update {
                                it.copy(
                                    isLoading = false,
                                    details = null,
                                    error = resource.message
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _detailsState.update {
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

    private fun getImages(id: Int) {
        viewModelScope.launch {
            _imagesState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getPersonImagesUseCase(id)
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _imagesState.update {
                                it.copy(
                                    isLoading = false,
                                    images = resource.data ?: emptyList(),
                                    error = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            _imagesState.update {
                                it.copy(
                                    isLoading = false,
                                    images = emptyList(),
                                    error = resource.message
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _imagesState.update {
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

    private fun getPersonCredits(id: Int) {
        viewModelScope.launch {
            _creditsState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getPersonCreditsUseCase(id)
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _creditsState.update {
                                it.copy(
                                    isLoading = false,
                                    crew = resource.data?.crew ?: emptyList(),
                                    cast = resource.data?.cast ?: emptyList(),
                                    error = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            _creditsState.update {
                                it.copy(
                                    isLoading = false,
                                    error = it.error
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _creditsState.update {
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