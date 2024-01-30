package com.example.mymovies.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.mymovies.data.remote.MovieApi

@Composable
fun PosterScreen(backStackEntry: NavBackStackEntry, navController: NavHostController) {
    val path = backStackEntry.arguments?.getString("posterPath")
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + path)
            .size(Size.ORIGINAL)
            .build()
    ).state
    val isNavigating = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (imageState is AsyncImagePainter.State.Success) {
            Image(
                painter = imageState.painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = Color.Transparent)
                    ) {
                        if (!isNavigating.value) navController.navigateUp()
                        isNavigating.value = true
                    }
            )
        } else {
            Image(
                imageVector = Icons.Rounded.ImageNotSupported,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}