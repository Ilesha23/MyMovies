package com.example.mymovies.ui.popular

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.mymovies.data.remote.MovieApi
import com.example.mymovies.domain.model.Movie

@Composable
fun PopularMovieScreen() {
    val viewModel = hiltViewModel<PopularMovieViewModel>()
    val movieState = viewModel.movieListState.collectAsState().value

    movieState.error?.let {
        Toast.makeText(LocalContext.current, stringResource(id = it), Toast.LENGTH_SHORT).show()
    }

    Surface {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(vertical = 4.dp),
                content = {
                    items(movieState.list.size) { index ->
                        MovieCard(movie = movieState.list[index])
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(movie: Movie) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + movie.poster_path)
            .size(Size.ORIGINAL)
            .build()
    ).state
    
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 4.dp
        ),
        onClick = {}
    ) {
        Column {
            Box(
                modifier = Modifier
                    .aspectRatio(9 / 16f)
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.BottomStart
            ) {
                if (imageState is AsyncImagePainter.State.Error) {
                    Image(
                        imageVector = Icons.Rounded.ImageNotSupported,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                } else if (imageState is AsyncImagePainter.State.Success) {
                    Image(
                        painter = imageState.painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                    )
                }
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = movie.title,
            )
        }
    }
}