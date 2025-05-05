package com.saira.imdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saira.imdb.domain.model.Movie
import com.saira.imdb.domain.repository.MovieRepository
import com.saira.imdb.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val uiState : StateFlow<UiState<List<Movie>>> = _uiState

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try{
                _uiState.value = movieRepository.getMovieList()
            } catch (e : Exception){
                _uiState.value = UiState.Error(e.message ?: "Unexpected error")
            }
        }
    }
}
