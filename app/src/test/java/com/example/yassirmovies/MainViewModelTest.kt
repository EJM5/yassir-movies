package com.example.yassirmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.yassirmovies.ui.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(FakeMovieRepository())
    }

    @Test
    fun `get movie data and check element`() {
        viewModel.getMovies()
        val movies = viewModel.movieLiveData.value?.results

        if (movies.isNullOrEmpty()) {
            fail("Could not get movie list")
        } else {
            val position = 0
            assertEquals(movies[position].id, position)
        }
    }

    @Test
    fun `get movie details and check data`() {
        val id = 0
        viewModel.getMovieDetails(id)
        val movie = viewModel.movieDetailsLiveData.value

        if (movie == null) {
            fail("Could not get movie details")
        } else {
            assertEquals(movie.id, id)
        }
    }

    @Test
    fun `get movie image config and check data`() {
        viewModel.getMovieConfig()
        val config = viewModel.movieConfigLiveData.value

        if (config == null) {
            fail("Could not get movie config")
        } else {
            assertEquals(config.images.baseUrl, "test")
        }
    }
}