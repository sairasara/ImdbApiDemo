package com.saira.imdb.data.api

import com.saira.imdb.data.model.MovieDto
import retrofit2.http.GET

interface ApiService {
    @GET("imdb/top250-movies")
    suspend fun getMovieList() : List<MovieDto>
}