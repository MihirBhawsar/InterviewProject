package com.example.demo2.model

data class MovieResponse(
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)