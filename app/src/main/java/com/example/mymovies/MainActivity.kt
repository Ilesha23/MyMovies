package com.example.mymovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mymovies.ui.LoginScreen
import com.example.mymovies.ui.ProfileScreen
import com.example.mymovies.ui.details.DetailsScreen
import com.example.mymovies.ui.details.PosterScreen
import com.example.mymovies.ui.popular.PopularMovieScreen
import com.example.mymovies.ui.theme.MyMoviesTheme
import com.example.mymovies.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoviesTheme {
                MainScreen(modifier = Modifier)
            }
        }
    }

//    @Preview(
//        name = "Dark",
//        widthDp = 320,
//        heightDp = 400,
//        showBackground = true,
//        uiMode = Configuration.UI_MODE_NIGHT_YES
//    )
//    @Preview(
//        name = "Light",
//        widthDp = 320,
//        heightDp = 400,
//        showBackground = true,
//        uiMode = Configuration.UI_MODE_NIGHT_NO
//    )
//    @Composable
//    fun MainScreenPreview() {
//        MainScreen(modifier = Modifier)
//    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(modifier: Modifier) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Popular.route) {
                    composable(Screen.Popular.route) {
                        PopularMovieScreen(navController)
                    }
                    composable(
                        Screen.Details.route + "/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { bacStackEntry ->
                        DetailsScreen(bacStackEntry, navController)
                    }
                    composable(
                        Screen.Poster.route + "/{posterPath}",
                        arguments = listOf(navArgument("posterPath") {type = NavType.StringType})
                    ) { backStackEntry ->
                        PosterScreen(backStackEntry, navController)
                    }
                    composable(Screen.Login.route) { backStackEntry ->
                        LoginScreen(
                            onNavigate = {
                                navController.navigate(Screen.Profile.route)
                            },
                            backStackEntry
                        )
                    }
                    composable(
                        Screen.Profile.route,
//                        arguments = listOf(
//
//                        )
                    ) { backStackEntry ->
                        ProfileScreen(backStackEntry)
                    }
                }

            }
        }
    }

}