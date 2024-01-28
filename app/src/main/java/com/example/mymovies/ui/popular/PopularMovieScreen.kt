package com.example.mymovies.ui.popular

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.mymovies.R
import com.example.mymovies.data.remote.MovieApi
import com.example.mymovies.domain.model.movie.Movie
import com.example.mymovies.util.Screen
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToInt

@Composable
fun PopularMovieScreen(
    navHostController: NavHostController
) {
    val viewModel = hiltViewModel<PopularMovieViewModel>()
    val movieState = viewModel.movieListState.collectAsState().value

    movieState.error?.let {
        Toast.makeText(LocalContext.current, stringResource(id = it), Toast.LENGTH_SHORT).show()
    }

    Surface {
        // TODO: maybe make smt scrollable
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (movieState.isLoading) Arrangement.Top else Arrangement.Center
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxHeight(if (movieState.isLoading) 0.94f else 1f),
                columns = GridCells.Fixed(2),
//                contentPadding = PaddingValues(bottom = 50.dp/*vertical = 4.dp*/),
                content = {
                    items(movieState.list.size) { index ->
                        MovieCard(movie = movieState.list[index], navHostController)
                        if (index == movieState.list.size - 1) {
                            viewModel.onEvent(MovieListUiEvent.Paginate)
                        }
                    }
                }
            )
            if (movieState.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
    movie: Movie,
    navHostController: NavHostController
) {
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
        shape = RoundedCornerShape(20.dp),
        onClick = {
            navHostController.navigate(Screen.Details.route + "/${movie.id}")
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(9 / 16f)
                    .fillMaxSize()
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
                            .padding(4.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = movie.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            runCatching {
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val dateFromString: Date = sdf.parse(movie.release_date)
                val today = Date()
                if (dateFromString.before(today) || dateFromString == today) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        text = ((movie.vote_average * 10).roundToInt().toDouble() / 10).toString(),
                        color =
                        if (movie.vote_average > 7)
                            Color.Green
                        else if (movie.vote_average < 4)
                            Color.Red
                        else Color.Yellow
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        text = stringResource(id = R.string.not_released_yes),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

        }
    }
}