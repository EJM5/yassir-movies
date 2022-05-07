package com.example.yassirmovies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yassirmovies.data.Movie
import com.example.yassirmovies.data.MovieConfig
import com.example.yassirmovies.data.MovieList
import com.example.yassirmovies.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {

    val movieLiveData: MutableLiveData<MovieList> = MutableLiveData()
    val movieConfigLiveData: MutableLiveData<MovieConfig> = MutableLiveData()
    val movieDetailsLiveData: MutableLiveData<Movie> = MutableLiveData()

    // Defaulted in case movie config call fails
    var imageBaseUrl = "https://image.tmdb.org/t/p/w500"

    var isInitialSetupReady = false

    fun getMovies() {
        repository.getMovies(movieLiveData)
    }

    fun getMovieConfig() {
        repository.getMovieConfig(movieConfigLiveData)
    }

    fun getMovieDetails(id: Int?) {
        repository.getMovieDetails(id ?: -1, movieDetailsLiveData)
    }
}