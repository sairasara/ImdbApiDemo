package com.saira.imdb.navigation

sealed class NavigationItem(val route: String) {
    object MovieList : NavigationItem("movieList")
    object MovieDetail : NavigationItem("movieDetail/{id}"){
        fun createRoute(id: String) = "movieDetail/$id"
    }
}