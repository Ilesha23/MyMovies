package com.example.mymovies.util

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Profile : Screen("profile")
    data object Home : Screen("main")
    data object Popular : Screen("popular")
    data object Upcoming : Screen("upcoming")
    data object Details : Screen("details")
}
