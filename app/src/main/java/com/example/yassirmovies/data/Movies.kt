package com.example.yassirmovies.data

import com.google.gson.annotations.SerializedName

data class MovieList(val results: List<Movie>)

data class Movie(val id: Int,
                 val overview: String,
                 @SerializedName("poster_path") val imageUrl: String?,
                 @SerializedName("release_date") val releaseDate: String,
                 @SerializedName("original_title") val title: String,
                 @SerializedName("vote_average") val voteAverage: Float,
                 @SerializedName("vote_count") val voteCount: Int
)

data class MovieConfig(val images: Image)

data class Image(@SerializedName("base_url") val baseUrl: String,
                 @SerializedName("poster_sizes") val posterSizes: List<String>)