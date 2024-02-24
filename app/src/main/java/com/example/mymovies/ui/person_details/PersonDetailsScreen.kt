package com.example.mymovies.ui.person_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.mymovies.R
import com.example.mymovies.data.remote.MovieApi.Companion.IMAGE_BASE_URL
import com.example.mymovies.domain.model.person.credits.PersonCast
import com.example.mymovies.domain.model.person.credits.PersonCrew
import kotlin.math.absoluteValue

@Composable
fun PersonDetailsScreen(backStackEntry: NavBackStackEntry, navController: NavHostController) {
    val viewModel = hiltViewModel<PersonDetailsViewModel>()
    val detailsState = viewModel.detailsState.collectAsState().value
    val creditsState = viewModel.creditsState.collectAsState().value
    val imagesState = viewModel.imagesState.collectAsState().value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = detailsState.details?.name.orEmpty(),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.size(16.dp))

            if (imagesState.images.isNotEmpty()) {
                PhotosPager(
                    photoUris = imagesState.images,
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 10f)
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = stringResource(id = R.string.biography),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Text(
                text =
                if (detailsState.details?.biography?.isBlank() == true)
                    stringResource(id = R.string.no_info_for_your_language) + " " + detailsState.details.biography
                else
                    detailsState.details?.biography.orEmpty(),
                modifier = Modifier
                    .padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            if (creditsState.cast?.isEmpty() == false) {
                Text(
                    text = stringResource(id = R.string.cast),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                CreditsRow(
                    items = creditsState.cast,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.7f)
                ) {
                    CastCard(it)
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            if (creditsState.crew?.isEmpty() == false) {
                Text(
                    text = stringResource(id = R.string.crew),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                CreditsRow(
                    items = creditsState.crew,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                ) {
                    CrewCard(it)
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosPager(photoUris: List<String>, modifier: Modifier = Modifier) {

    val pagerState = rememberPagerState {
        photoUris.size
    }

    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(1)
    )

    val padding = LocalConfiguration.current.screenWidthDp / 3.4

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = padding.dp),
        flingBehavior = fling
    ) { page ->
        Card(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
            modifier = Modifier
                .fillMaxHeight()
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                    scaleX = lerp(
                        start = 0.8f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                    scaleY = lerp(
                        start = 0.8f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            PhotoCard(uri = photoUris[page])
        }
    }

}

@Composable
fun PhotoCard(uri: String) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(IMAGE_BASE_URL + uri)
            .size(Size.ORIGINAL)
            .build()
    ).state

    if (imageState is AsyncImagePainter.State.Success) {
        Image(
            painter = imageState.painter,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
        )
    } else {
        Image(imageVector = Icons.Rounded.ImageNotSupported, contentDescription = null)
    }
}

@Composable
fun <T> CreditsRow(
    items: List<T>,
    modifier: Modifier = Modifier,
    item: @Composable (item: T) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) {
            item(it)
        }
    }
}

@Composable
fun CastCard(cast: PersonCast) {
    val posterState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(IMAGE_BASE_URL + cast.poster_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Column(
        modifier = Modifier
            .aspectRatio(0.6f)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (posterState is AsyncImagePainter.State.Success) {
                Image(
                    painter = posterState.painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(0.667f)
                        .clip(CardDefaults.shape)
                )
            } else {
                Image(
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.aspectRatio(0.667f)
                )
            }
            Text(
                text = cast.title,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun CrewCard(crew: PersonCrew) {
    val posterState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(IMAGE_BASE_URL + crew.poster_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Column(
        modifier = Modifier
            .aspectRatio(0.545f)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (posterState is AsyncImagePainter.State.Success) {
                Image(
                    painter = posterState.painter,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .aspectRatio(0.667f)
                )
            } else {
                Image(
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.aspectRatio(0.667f)
                )
            }
            Text(
                text = crew.job,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
            Text(
                text = crew.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
}