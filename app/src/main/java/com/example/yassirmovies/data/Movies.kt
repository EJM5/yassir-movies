package com.example.yassirmovies.data

import com.google.gson.annotations.SerializedName

data class MovieList(val results: List<Movie>)

data class Movie(val id: Int,
                 val overview: String,
                 @SerializedName("poster_path") val imageUrl: String?,
                 @SerializedName("release_date") val releaseDate: String,
                 @SerializedName("original_title") val title: String)