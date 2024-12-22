package com.example.demo2.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo2.Util.EndlessScrollListener
import com.example.demo2.adapter.MovieAdapter
import com.example.demo2.databinding.ActivityMainBinding
import com.example.demo2.model.Movie
import com.example.demo2.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding
private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MovieViewModel by viewModels()
    private val movieAdapter by lazy { MovieAdapter(moviesList, this::onClickItem) }
    private val moviesList = mutableListOf<Movie>()

    private val mapping = mutableMapOf(
        "page" to "1",
        "apiKey" to "4d761811",
        "s" to "romance"
    )

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        setupScrollListener()
        viewModel.fetchMovies(mapping)
    }

    private fun setupRecyclerView() {
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun setupScrollListener() {
        binding.recycleView.addOnScrollListener(object : EndlessScrollListener() {
            override fun onLoadMore(page: Int) {
                Log.d(TAG, "onLoadMore")
                mapping["page"] = page.toString()
                viewModel.fetchMovies(mapping)
            }
        })
    }

    private fun observeViewModel() {
        viewModel.movies.observe(this) { movies ->
            movies?.let {
                moviesList.addAll(it)
                movieAdapter.notifyDataSetChanged()
            }
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Log.e(TAG, it)
            }
        }
    }


    private fun onClickItem(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("movie", movie)
        }
        startActivity(intent)
    }
}
