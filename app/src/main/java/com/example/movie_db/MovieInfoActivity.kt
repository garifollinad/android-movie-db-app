package com.example.movie_db

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieInfoActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_info_activity)
        val title: TextView = findViewById(R.id.title)
        val back: ImageButton = findViewById(R.id.back)
        val review: TextView = findViewById(R.id.overview)
        val imagePoster: ImageView = findViewById(R.id.poster)
        val rating: TextView = findViewById(R.id.rate)
        val decorView: View = window.decorView
        val options: Int = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = options
        val releaseDate: TextView = findViewById(R.id.releasedate)
        val popularity: TextView = findViewById(R.id.popularity)
        val adultContent: TextView = findViewById(R.id.adult)

        back.setOnClickListener {
            onBackPressed()
        }

        Glide.with(this).load(intent.extras?.getString("backdrop_path")).into(imagePoster)
        title.text = intent.extras?.getString("title")

        releaseDate.text = intent.extras?.getString("release_date")
        if (intent.getBooleanExtra("adult", false)) {
            adultContent.text = "18+"
        } else {
            adultContent.text = "12+"
        }
        rating.text = intent.extras?.getString("vote_average")
        popularity.text = intent.extras?.getString("popularity")
        review.text = intent.extras?.getString("overview")
    }
}