package com.example.yassirmovies.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yassirmovies.R
import com.example.yassirmovies.data.Movie

class MovieAdapter(private val context: Context,
                   private val movies: List<Movie>,
                   private val imageUrl: String,
                   private val listener: OnMovieItemClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.setMovie(movie, imageUrl)
        holder.itemView.setOnClickListener { listener.onMovieClick(movie) }
    }

    override fun getItemCount() = movies.size

    interface OnMovieItemClickListener {
        fun onMovieClick(movie: Movie)
    }
}