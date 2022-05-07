package com.example.yassirmovies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yassirmovies.data.MovieList
import com.example.yassirmovies.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {

    val movieLiveData: MutableLiveData<MovieList> = MutableLiveData()

    fun getMovies() {
        repository.getMovies(movieLiveData)
    }
}