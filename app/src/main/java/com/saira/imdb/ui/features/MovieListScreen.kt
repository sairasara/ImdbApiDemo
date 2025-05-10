package com.saira.imdb.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.saira.imdb.R
import com.saira.imdb.domain.model.Movie
import com.saira.imdb.navigation.NavigationItem
import com.saira.imdb.ui.common.UiState
import com.saira.imdb.ui.viewmodel.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen (viewModel: MoviesViewModel = hiltViewModel(),
                     navController: NavController) {

    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredMovies by viewModel.filteredMovies.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF162936))
    ) {
        when (uiState) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                //val movies = (uiState as UiState.Success<List<Movie>>).data

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.onSearchQueryChanged(it) },
                    label = { Text("Search movies...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.Yellow,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Yellow,
                        unfocusedIndicatorColor = Color.Gray)
                )

                LazyVerticalGrid(
                    columns =  GridCells.Adaptive(minSize = 150.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    content = {
                        items(filteredMovies.size) { index ->
                            MovieItem(
                                movie = filteredMovies[index],
                                onClick = {
                                    navController.navigate(NavigationItem.MovieDetail.createRoute(filteredMovies[index].id))
                                }
                            )
                        }
                    }
                )
            }
            is UiState.Error -> {
                val errorMessage = (uiState as UiState.Error).message
                Text("Error: $errorMessage")
            }
        }
    }
}


@Composable
fun MovieItem(movie: Movie,  onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.7f)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
           // .clickable(onClick = onClick),
        ) {
        Column(modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.imageUrl)
                    .placeholder(R.drawable.movie_placeholder)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}