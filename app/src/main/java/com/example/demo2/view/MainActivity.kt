package com.example.demo2.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo2.ClickListener.OnMovieClickListener
import com.example.demo2.Util.EndlessScrollListener
import com.example.demo2.adapter.MovieAdapter
import com.example.demo2.databinding.ActivityMainBinding
import com.example.demo2.model.Movie
import com.example.demo2.network.ApiClient
import com.example.demo2.repository.MovieRepository
import com.example.demo2.viewmodel.MovieViewModel
import com.example.demo2.viewmodel.MovieViewModelFactory

class MainActivity : AppCompatActivity(), OnMovieClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private var mapping: MutableMap<String, String> = mutableMapOf(
        "page" to "1",
        "apiKey" to "4d761811",
        "s" to "romance"
    )
    private val moviesList: MutableList<Movie> = mutableListOf()

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        val repository = MovieRepository(ApiClient.instance)
        val viewModelFactory = MovieViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]

        binding.recycleView.addOnScrollListener(object : EndlessScrollListener() {
            override fun onLoadMore(page: Int) {
                Log.d(TAG, "onLoadMore")
                mapping["page"] = page.toString()
                viewModel.fetchMovies(mapping)
            }
        })

        viewModel.fetchMovies(mapping)

        viewModel.movies.observe(this, Observer { movies ->
            movies?.let {
                // Update the list and notify the adapter
                if (moviesList != null) {
                    moviesList.addAll(it)
                    movieAdapter.notifyDataSetChanged() // Notify the adapter about changes}
                }
            }
        })

        viewModel.error.observe(this, Observer { error ->
            error?.let {
                Log.e(TAG, it)
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.setHasFixedSize(true)
        movieAdapter = MovieAdapter(moviesList, this)
        binding.recycleView.adapter = movieAdapter
    }

    override fun onMovieClick(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}