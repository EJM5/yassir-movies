package com.example.yassirmovies.repositories

import androidx.lifecycle.MutableLiveData
import com.example.yassirmovies.BuildConfig
import com.example.yassirmovies.data.Image
import com.example.yassirmovies.data.Movie
import com.example.yassirmovies.data.MovieConfig
import com.example.yassirmovies.data.MovieList
import com.example.yassirmovies.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) : MovieRepository {
    override fun getMovies(liveData: MutableLiveData<MovieList>) {
        val call: Call<MovieList> = movieService.getMovieList(BuildConfig.API_KEY)
        call.enqueue(object : Callback<MovieList> {
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                liveData.postValue(MovieList(listOf()))
            }

            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.isSuccessful) {
                    liveData.postValue(response.body())
                } else {
                    liveData.postValue(MovieList(listOf()))
                }
            }
        })
    }

    override fun getMovieDetails(movieId: Int, liveData: MutableLiveData<Movie>) {
        val call: Call<Movie> = movieService.getMovieDetails(movieId, BuildConfig.API_KEY)
        call.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                // TODO: implement on failure
//                liveData.postValue(Movie())
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    liveData.postValue(response.body())
                } else {
                    // TODO: implement on failure
                    liveData.postValue(response.body())
                }
            }
        })
    }

    override fun getMovieConfig(liveData: MutableLiveData<MovieConfig>) {
        val defaultBaseUrl = "https://image.tmdb.org/t/p"
        val defaultPosterSizes = listOf("/w500")

        val call: Call<MovieConfig> = movieService.getMovieConfig(BuildConfig.API_KEY)
        call.enqueue(object : Callback<MovieConfig> {
            override fun onFailure(call: Call<MovieConfig>, t: Throwable) {
                liveData.postValue(MovieConfig(Image(baseUrl = defaultBaseUrl, posterSizes = defaultPosterSizes)))
            }

            override fun onResponse(call: Call<MovieConfig>, response: Response<MovieConfig>) {
                if (response.isSuccessful) {
                    liveData.postValue(response.body())
                } else {
                    liveData.postValue(MovieConfig(Image(baseUrl = defaultBaseUrl, posterSizes = defaultPosterSizes)))
                }
            }
        })
    }
}