package com.example.mymovies.ui.popular

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PopularMovieScreen() {
    val viewModel = hiltViewModel<PopularMovieViewModel>()
    val movieState = viewModel.movieListState.collectAsState().value

    Surface {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                content = {
                    items(movieState.list.size) { index ->
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 8.dp
                            ),
                        ) {
                            Box(
                                Modifier.aspectRatio(9/16f),
                                contentAlignment = Alignment.BottomStart
                            ) {
                                Text(
                                    modifier = Modifier.padding(start = 8.dp),
                                    text = movieState.list[index].title,
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}