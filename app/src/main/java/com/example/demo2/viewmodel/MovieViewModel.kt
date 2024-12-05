package com.example.demo2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo2.model.Movie
import com.example.demo2.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchMovies(map: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getMovies(map)
            if (response.isSuccessful) {
                try{
                    response.body()?.let {
                        _movies.postValue(it.Search)
                    }
                }catch (e:Exception) {
                    _error.postValue("An error occurred: ${e.message}")
                }

            } else {
                _error.postValue("Error fetching movies")
            }
        }
    }
}
