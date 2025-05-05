package com.saira.imdb.data.mapper

import com.saira.imdb.data.model.MovieDto
import com.saira.imdb.domain.model.Movie

object MovieMapper {

    fun toDomain(movieDto: MovieDto) : Movie {
        return ( Movie(
            id = movieDto.id.orEmpty(),
            title = movieDto.primaryTitle ?: movieDto.originalTitle ?: "Untitled",
            description = movieDto.description,
            imageUrl = movieDto.primaryImage,
            rating = movieDto.averageRating,
            budget = movieDto.budget,
            grossWorldwide = movieDto.grossWorldwide,
            runtimeMinutes = movieDto.runtimeMinutes,
            releaseDate = movieDto.releaseDate,
            language = movieDto.language,
            genres = movieDto.genres ?: emptyList(),
            countriesOfOrigin = movieDto.countriesOfOrigin ?: emptyList(),
            spokenLanguages = movieDto.spokenLanguages ?: emptyList(),
            isAdult = movieDto.isAdult == true,
            type = movieDto.type,
            url = movieDto.url
        ))
    }
}