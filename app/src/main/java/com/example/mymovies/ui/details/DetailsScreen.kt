package com.example.mymovies.ui.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.mymovies.data.remote.MovieApi
import com.example.mymovies.util.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(bacStackEntry: NavBackStackEntry, navController: NavHostController) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val detailsState = viewModel.detailsState.collectAsState().value
    val backdropsState = viewModel.imagesState.collectAsState().value
    val posterState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.details?.poster_path)
            .size(Size.ORIGINAL)
            .build()
    ).state
    var expanded by remember { mutableStateOf(false) }


    detailsState.error?.let {
//        Toast.makeText(LocalContext.current, stringResource(id = it), Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
//            verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
//                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            InfiniteImageSlider(backdropsState.images)
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
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false, radius = 0.dp)
                        ) {
                            navController.navigate(Screen.Poster.route + detailsState.details?.poster_path)
                        }
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
                Text(
                    text = (viewModel.convertCurrencyToString(
                        detailsState.details?.budget ?: 0
                    ))
                )
                Text(
                    text = (viewModel.convertCurrencyToString(
                        detailsState.details?.revenue ?: 0
                    ))
                )
                Text(text = detailsState.details?.vote_average.toString())
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = detailsState.details?.tagline ?: "", textAlign = TextAlign.Center)
            Text(
                text = detailsState.details?.overview ?: "",
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .clickable {
                        expanded = !expanded
                    }
            )
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfiniteImageSlider(paths: List<String>) {
    val images = paths.take(30)
    val pagerState = rememberPagerState(pageCount = {
        images.size
    })
    var pagerScrollState by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            SliderImage(imagePath = images[page])
        }

        LaunchedEffect(key1 = pagerScrollState) {
            launch {
                delay(2000)
                pagerState.animateScrollToPage((pagerState.currentPage + 1) % pagerState.pageCount)
                pagerScrollState = !pagerScrollState
            }
        }

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(4.dp)
                )
            }
        }
    }
}

@Composable
fun SliderImage(imagePath: String) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + imagePath)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (imageState is AsyncImagePainter.State.Success) {
            Image(
                painter = imageState.painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        } else {
            Image(
                imageVector = Icons.Rounded.ImageNotSupported,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}