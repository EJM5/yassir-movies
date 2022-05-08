package com.example.yassirmovies

import androidx.lifecycle.MutableLiveData
import com.example.yassirmovies.data.Image
import com.example.yassirmovies.data.Movie
import com.example.yassirmovies.data.MovieConfig
import com.example.yassirmovies.data.MovieList
import com.example.yassirmovies.repositories.MovieRepository

class FakeMovieRepository : MovieRepository {
    override fun getMovies(liveData: MutableLiveData<MovieList>) {
        liveData.value = MovieList(listOf(
            Movie(id = 0, overview = "Overview 1", imageUrl = ".jpg", releaseDate = "2022-12-22", title = "Title 1", voteAverage = 7.2f, voteCount = 1002),
            Movie(id = 1, overview = "Overview 2", imageUrl = ".jpg", releaseDate = "1995-11-27", title = "Title 2", voteAverage = 1.0f, voteCount = 123),
            Movie(id = 2, overview = "Overview 3", imageUrl = ".jpg", releaseDate = "2001-10-01", title = "Title 3", voteAverage = 6.3f, voteCount = 8935)
        ))
    }

    override fun getMovieDetails(movieId: Int, liveData: MutableLiveData<Movie>) {
        liveData.value = Movie(id = movieId, overview = "Overview 3", imageUrl = ".jpg", releaseDate = "2001-10-01", title = "Title 3", voteAverage = 6.3f, voteCount = 8935)
    }

    override fun getMovieConfig(liveData: MutableLiveData<MovieConfig>) {
        liveData.value = MovieConfig(Image(baseUrl = "test", posterSizes = listOf("50x50")))
    }
}