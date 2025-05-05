package com.saira.imdb.navigation

sealed class NavigationItem(val route: String) {
    object MovieList : NavigationItem("movieList")
}