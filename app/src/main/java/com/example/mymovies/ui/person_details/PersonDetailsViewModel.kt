package com.example.mymovies.ui.person_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.data.repository.person.PersonDetailsRepository
import com.example.mymovies.ui.details.DetailsState
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
    private val personDetailsRepository: PersonDetailsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val personId = savedStateHandle.get<Int>("personId")

    private val _detailsState = MutableStateFlow(PersonDetailsState(details = null))
    val detailsState = _detailsState.asStateFlow()

    init {
        getDetails(personId ?: -1)
    }

    private fun getDetails(id: Int) {

        viewModelScope.launch {
            _detailsState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            personDetailsRepository.getPersonDetails(id)
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

}