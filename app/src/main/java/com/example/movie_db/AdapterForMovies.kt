package com.example.movie_db

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterForMovies(var context: Context, var movies: List<Movie>) : RecyclerView.Adapter<AdapterForMovies.MyViewHolder>() {
    inner class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Movie?) {
            val title = view.findViewById<TextView>(R.id.title)
            val mainPoster = view.findViewById<ImageView>(R.id.mainPoster)

            title.text = post?.title

            Glide.with(context)
                .load(post?.getPathToPoster())
                .into(mainPoster)

            view.setOnClickListener {
                val intent= Intent(view.context, MovieInfoActivity::class.java)
                intent.putExtra("title", post?.title)
                intent.putExtra("poster_path", post?.getPathToPoster())
                intent.putExtra("backdrop_path", post?.getPathToBackground())
                intent.putExtra("overview", post?.review)
                intent.putExtra("vote_average", (post?.voteRating).toString())
                intent.putExtra("release_date", post?.releaseDate)
                intent.putExtra("adult", post?.adultContent)
                intent.putExtra("popularity", (post?.rating).toString())
                intent.putExtra("movie_id", post?.movieId)
                view.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.skeleton, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: AdapterForMovies.MyViewHolder, i: Int) {
        viewHolder.bind(movies[i])
    }
}