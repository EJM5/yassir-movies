package com.example.yassirmovies.network

import com.example.yassirmovies.data.Movie
import com.example.yassirmovies.data.MovieConfig
import com.example.yassirmovies.data.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun getMovieList(@Query("api_key") apiKey: String): Call<MovieList>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int,
                        @Query("api_key") apiKey: String): Call<Movie>

    @GET("configuration")
    fun getMovieConfig(@Query("api_key") apiKey: String): Call<MovieConfig>
}