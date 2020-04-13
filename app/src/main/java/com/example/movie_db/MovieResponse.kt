package com.example.movie_db

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("results")
    private val results: List<Movie>? = null
    @SerializedName("total_results")
    fun getResults(): List<Movie?>? {
        return results
    }
}