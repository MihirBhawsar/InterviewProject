package com.example.demo2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo2.ClickListener.OnMovieClickListener
import com.example.demo2.databinding.MovieLayoutBinding
import com.example.demo2.model.Movie

class MovieAdapter(private val movies: List<Movie>,private val listener: OnMovieClickListener
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.textViewTitle.text = movie.Title
            binding.textViewBody.text = movie.imdbID
            binding.textViewViews.text = movie.Year

            // Handle item click by passing the click event to the listener
            binding.root.setOnClickListener {
                listener.onMovieClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}
