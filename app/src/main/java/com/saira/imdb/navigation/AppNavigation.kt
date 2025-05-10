package com.saira.imdb.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saira.imdb.ui.features.MovieDetailScreen
import com.saira.imdb.ui.features.MovieListScreen
import com.saira.imdb.ui.viewmodel.MoviesViewModel


@Composable
fun AppNavigation(navController: NavHostController, viewModel: MoviesViewModel = hiltViewModel()){

    NavHost(navController = navController,
        startDestination = NavigationItem.MovieList.route){

        composable(NavigationItem.MovieList.route) {
            MovieListScreen(viewModel,navController)
        }
        composable(NavigationItem.MovieDetail.route){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val movie = viewModel.getMovieById(id)
            if (movie != null) {
                MovieDetailScreen(navController,movie)
            } else {
                Text("Movie not found")
            }
        }
    }
}