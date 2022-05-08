package com.example.yassirmovies.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yassirmovies.data.Movie
import com.example.yassirmovies.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(), MovieAdapter.OnMovieItemClickListener {

    companion object {
        val tag = MovieListFragment::class.java.simpleName ?: ""
    }

    private val viewModel: MainViewModel by activityViewModels()

    private var binding: FragmentMovieListBinding? = null
    private var listener: OnMovieListInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieLiveData.observe(this, {
            setMovieList(it.results)
        })

        binding?.refreshLayout?.setOnClickListener {
            binding?.refreshLayout?.visibility = View.GONE
            viewModel.getMovies()
        }

        setProgressLoader()
        viewModel.getMovies()
    }

    private fun setProgressLoader() {
        binding?.movieRecyclerView?.visibility = View.GONE
        binding?.errorText?.visibility = View.GONE
        binding?.progressLoader?.show()
    }

    private fun setMovieList(movies: List<Movie>) {
        binding?.progressLoader?.hide()
        if (movies.isNullOrEmpty()) {
            binding?.movieRecyclerView?.visibility = View.GONE
            binding?.refreshLayout?.visibility = View.VISIBLE
            binding?.errorText?.visibility = View.VISIBLE
        } else {
            binding?.movieRecyclerView?.visibility = View.VISIBLE
            binding?.refreshLayout?.visibility = View.GONE
            binding?.errorText?.visibility = View.GONE
            context?.let {
                binding?.movieRecyclerView?.layoutManager = LinearLayoutManager(context)
                binding?.movieRecyclerView?.adapter = MovieAdapter(it, movies, viewModel.imageBaseUrl, this)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMovieListInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onMovieClick(movie: Movie) {
        listener?.onMovieClick(movie)
    }

    interface OnMovieListInteractionListener {
        fun onMovieClick(movie: Movie)
    }
}