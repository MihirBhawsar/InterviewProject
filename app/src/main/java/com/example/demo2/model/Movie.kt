package com.example.demo2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
) : Parcelable