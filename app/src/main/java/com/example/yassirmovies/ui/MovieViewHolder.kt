package com.example.yassirmovies.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yassirmovies.R
import com.example.yassirmovies.data.Movie
import com.example.yassirmovies.utils.load

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val moviePoster = itemView.findViewById<ImageView>(R.id.poster_image)
    private val titleText = itemView.findViewById<TextView>(R.id.title_text)
    private val releaseDateText = itemView.findViewById<TextView>(R.id.release_date_text)

    fun setMovie(movie: Movie, baseImageUrl: String) {
        val fullImageUrl = baseImageUrl + movie.imageUrl
        moviePoster.load(fullImageUrl, R.drawable.ic_movie)

        titleText.text = movie.title
        releaseDateText.text = movie.releaseDate
    }
}