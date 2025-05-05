package com.saira.imdb.data.repository

import com.saira.imdb.data.api.ApiService
import com.saira.imdb.data.mapper.MovieMapper
import com.saira.imdb.domain.model.Movie
import com.saira.imdb.domain.repository.MovieRepository
import com.saira.imdb.ui.common.UiState
import com.saira.imdb.utils.NetworkUtils
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkUtils: NetworkUtils) : MovieRepository {

    override suspend fun getMovieList() : UiState<List<Movie>> {

        return if (networkUtils.isInternetAvailable()) {
            try {
                val response = apiService.getMovieList()
                val movieList = response.map { MovieMapper.toDomain(it) }
                UiState.Success(movieList)
            } catch (e: Exception) {
                UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        } else {
            UiState.Error("No internet connection")
        }
    }
}