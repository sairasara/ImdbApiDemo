package com.saira.imdb.data.model

import com.squareup.moshi.Json

data class MovieDto(

    @Json(name = "averageRating")
    val averageRating: Double?,

    @Json(name = "budget")
    val budget: Long?,

    @Json(name = "contentRating")
    val contentRating: String?,

    @Json(name = "countriesOfOrigin")
    val countriesOfOrigin: List<String>?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "endYear")
    val endYear: Any?,

    @Json(name = "externalLinks")
    val externalLinks: List<String>?,

    @Json(name = "filmingLocations")
    val filmingLocations: List<String>?,

    @Json(name = "genres")
    val genres: List<String>?,

    @Json(name = "grossWorldwide")
    val grossWorldwide: Long?,

    @Json(name = "id")
    val id: String?,

    @Json(name = "interests")
    val interests: List<String>?,

    @Json(name = "isAdult")
    val isAdult: Boolean?,

    @Json(name = "language")
    val language: String?,

    @Json(name = "numVotes")
    val numVotes: Int?,

    @Json(name = "originalTitle")
    val originalTitle: String?,

    @Json(name = "primaryImage")
    val primaryImage: String?,

    @Json(name = "primaryTitle")
    val primaryTitle: String?,

    @Json(name = "releaseDate")
    val releaseDate: String?,

    @Json(name = "runtimeMinutes")
    val runtimeMinutes: Int?,

    @Json(name = "spokenLanguages")
    val spokenLanguages: List<String>?,

    @Json(name = "startYear")
    val startYear: Int?,

    @Json(name = "type")
    val type: String?,

    @Json(name = "url")
    val url: String?
)