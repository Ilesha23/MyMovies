package com.example.mymovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mymovies.ui.LoginScreen
import com.example.mymovies.ui.ProfileScreen
import com.example.mymovies.ui.details.DetailsScreen
import com.example.mymovies.ui.details.PosterScreen
import com.example.mymovies.ui.movies.PopularMovieScreen
import com.example.mymovies.ui.person_details.PersonDetailsScreen
import com.example.mymovies.ui.theme.MyMoviesTheme
import com.example.mymovies.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoviesTheme {

                Scaffold(
                    topBar = {},
                    bottomBar = {
                        BottomAppBar(
                            modifier = Modifier.height(60.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                IconButton(
                                    onClick = { /*TODO*/ }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Movie,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Tv,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                    },
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        MainScreen(modifier = Modifier)
                    }
                }

            }
        }
    }

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
                        Screen.PersonDetails.route + "/{personId}",
                        arguments = listOf(navArgument("personId") { type = NavType.IntType })
                    )
                    { backStackEntry ->
                        PersonDetailsScreen(backStackEntry, navController)
                    }
                    composable(
                        Screen.Poster.route + "/{posterPath}",
                        arguments = listOf(navArgument("posterPath") { type = NavType.StringType })
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
                    ) { backStackEntry ->
                        ProfileScreen(backStackEntry)
                    }
                }

            }
        }
    }

}