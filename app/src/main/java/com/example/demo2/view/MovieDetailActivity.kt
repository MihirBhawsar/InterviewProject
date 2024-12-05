package com.example.demo2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo2.databinding.ActivityMovieDetailBinding
import com.example.demo2.model.Movie
import com.bumptech.glide.Glide


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getSerializableExtra("movie") as? Movie
        movie?.let {
            binding.title.text = it.Title
            binding.year.text = it.Year
            Glide.with(this).load(it.Poster).into(binding.poster)
        }
    }
}
