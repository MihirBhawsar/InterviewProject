package com.example.demo2.repository
import com.example.demo2.network.ApiService
import javax.inject.Inject


class MovieRepository @Inject constructor(private val api: ApiService) {
    suspend fun getMovies(map: Map<String, String>) = api.getMovies(map)
}