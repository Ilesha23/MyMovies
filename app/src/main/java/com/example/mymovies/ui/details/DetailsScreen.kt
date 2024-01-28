package com.example.mymovies.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.mymovies.data.remote.MovieApi

@Composable
fun DetailsScreen(bacStackEntry: NavBackStackEntry) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val detailsState = viewModel.detailsState.collectAsState().value
    val backdropState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.BACKDROP_BASE_URL + detailsState.details?.backdrop_path)
            .size(Size.ORIGINAL)
            .build()
    ).state
    val posterState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.details?.poster_path)
            .size(Size.ORIGINAL)
            .build()
    ).state


    detailsState.error?.let {
//        Toast.makeText(LocalContext.current, stringResource(id = it), Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
//            verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
//                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            if (backdropState is AsyncImagePainter.State.Success) {
                Image(
                    painter = backdropState.painter,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            } else {
                Image(
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize() // TODO:
                )
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
        ) {
            if (posterState is AsyncImagePainter.State.Success) {
                Image(
                    painter = posterState.painter,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                )
            } else {
                Image(
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize() // TODO:
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = detailsState.details?.title ?: "", textAlign = TextAlign.Center)
//                Text(text = detailsState.details?.tagline ?: "", textAlign = TextAlign.Center)
                Text(text = detailsState.details?.release_date ?: "")
                Text(text = (viewModel.convertCurrencyToString(detailsState.details?.budget ?: 0)))
                Text(text = (viewModel.convertCurrencyToString(detailsState.details?.revenue ?: 0)))
                Text(text = detailsState.details?.vote_average.toString())
            }
        }

        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = detailsState.details?.tagline ?: "", textAlign = TextAlign.Center)
            Text(text = detailsState.details?.overview ?: "")
        }

    }
}