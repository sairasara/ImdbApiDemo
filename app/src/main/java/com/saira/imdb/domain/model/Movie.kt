package com.saira.imdb.domain.model

data class Movie(

    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val rating: Double?,
    val budget: Long?,
    val grossWorldwide: Long?,
    val runtimeMinutes: Int?,
    val releaseDate: String?,
    val language: String?,
    val genres: List<String>,
    val countriesOfOrigin: List<String>,
    val spokenLanguages: List<String>,
    val isAdult: Boolean,
    val type: String?,
    val url: String?

//    val averageRating: Double,
//    val contentRating: String,
//    val endYear: Any,
//    val externalLinks: List<String>,
//    val filmingLocations: List<String>,
//    val interests: List<String>,
//    val numVotes: Int,
//    val startYear: Int
)