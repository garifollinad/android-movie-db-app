package com.example.movie_db

import com.google.gson.annotations.SerializedName

private var baseImageUrl: String = "https://image.tmdb.org/t/p/w500"

class Movie {
    @SerializedName("movie_id")
    var movieId: Int? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("overview")
    var review: String? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    @SerializedName("popularity")
    var rating: Double? = null
    @SerializedName("vote_average")
    var voteRating: Double? = null
    @SerializedName("adult")
    var adultContent = false
    @SerializedName("backdrop_path")
    private var pathToBackground: String? = null
    @SerializedName("poster_path")
    private var pathToPoster: String? = null

    fun getPathToPoster():String {
        return baseImageUrl + pathToPoster
    }
    fun getPathToBackground():String{
        return baseImageUrl + pathToBackground
    }
}