package com.saira.imdb.domain.repository

import com.saira.imdb.domain.model.Movie
import com.saira.imdb.ui.common.UiState

interface MovieRepository {
    suspend fun getMovieList() : UiState<List<Movie>>
}