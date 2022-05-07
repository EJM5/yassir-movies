package com.example.yassirmovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.yassirmovies.R
import com.example.yassirmovies.data.Movie
import com.example.yassirmovies.databinding.FragmentMovieDetailsBinding
import com.example.yassirmovies.utils.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    companion object {
        val tag = MovieDetailsFragment::class.java.simpleName ?: ""
    }

    private val viewModel: MainViewModel by activityViewModels()

    private var binding: FragmentMovieDetailsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieDetailsLiveData.observe(this, {
            if (binding?.titleText?.text.isNullOrBlank()) {
                setOriginalMovieDetails(it)
            } else {
                setRemainingMovieDetails(it)
            }
        })

        viewModel.getMovieDetails(viewModel.movieDetailsLiveData.value?.id)
    }

    private fun setOriginalMovieDetails(movie: Movie) {
        // TODO: Change placeholder
        binding?.posterImage?.load(viewModel.imageBaseUrl + movie.imageUrl, R.drawable.ic_launcher_foreground)
        binding?.titleText?.text = movie.title
        binding?.releaseDateText?.text = movie.releaseDate
        binding?.overviewText?.text = movie.overview
    }

    private fun setRemainingMovieDetails(movie: Movie) {
        binding?.ratingBar?.visibility = View.VISIBLE
        binding?.voteCountText?.visibility = View.VISIBLE

        binding?.ratingBar?.max = 100
        binding?.ratingBar?.progress = (movie.voteAverage * 10).toInt()
        binding?.ratingBar?.animate()
        binding?.voteCountText?.text = getString(R.string.vote_count, movie.voteCount)
    }
}