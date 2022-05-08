package com.example.yassirmovies.repositories

import androidx.lifecycle.MutableLiveData
import com.example.yassirmovies.data.Movie
import com.example.yassirmovies.data.MovieConfig
import com.example.yassirmovies.data.MovieList

interface MovieRepository {
    fun getMovies(liveData: MutableLiveData<MovieList>)

    fun getMovieDetails(movieId: Int, liveData: MutableLiveData<Movie>)

    fun getMovieConfig(liveData: MutableLiveData<MovieConfig>)
}