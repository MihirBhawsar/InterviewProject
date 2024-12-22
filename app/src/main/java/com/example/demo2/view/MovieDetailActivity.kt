package com.example.demo2.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo2.databinding.ActivityMovieDetailBinding
import com.example.demo2.model.Movie
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("movie", Movie::class.java)
        } else {
            @Suppress("DEPRECATION") // Suppress deprecation warning for older API levels
            intent.getParcelableExtra("movie")
        }
        movie?.let {
            binding.title.text = it.Title
            binding.year.text = it.Year
            Glide.with(this).load(it.Poster).into(binding.poster)
        }
    }
}
