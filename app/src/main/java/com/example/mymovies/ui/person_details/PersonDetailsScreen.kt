package com.example.mymovies.ui.person_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
fun PersonDetailsScreen(backStackEntry: NavBackStackEntry, navController: NavHostController) {
    val viewModel = hiltViewModel<PersonDetailsViewModel>()
    val detailsState = viewModel.detailsState.collectAsState().value

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PhotosPager(photoUris = detailsState.images)
        Text(text = detailsState.details?.biography.orEmpty())
    }

}

@Composable
fun PhotosPager(photoUris: List<String>) {
    
}