package com.example.demo2.network

import com.example.demo2.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("/")
   suspend fun getMovies(@QueryMap map: Map<String, String>): Response<MovieResponse>
}