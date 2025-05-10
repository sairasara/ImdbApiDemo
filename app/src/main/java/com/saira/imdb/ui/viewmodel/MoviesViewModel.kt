package com.saira.imdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saira.imdb.domain.model.Movie
import com.saira.imdb.domain.repository.MovieRepository
import com.saira.imdb.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val uiState : StateFlow<UiState<List<Movie>>> = _uiState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _allMovies = MutableStateFlow<List<Movie>>(emptyList())

    val filteredMovies: StateFlow<List<Movie>> = _searchQuery
        .combine(_allMovies) { query, movies ->
            if (query.isBlank()) movies
            else movies.filter { it.title.contains(query, ignoreCase = true) }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try{
              //  _uiState.value = movieRepository.getMovieList()

                val result = movieRepository.getMovieList()
                if (result is UiState.Success) {
                    _allMovies.value = result.data
                }
                _uiState.value = result

            } catch (e : Exception){
                _uiState.value = UiState.Error(e.message ?: "Unexpected error")
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun getMovieById(id : String?) : Movie? {
        val currentState = uiState.value
        return if (currentState is UiState.Success) {
            currentState.data.find { it.id == id }
        } else null
    }
}
