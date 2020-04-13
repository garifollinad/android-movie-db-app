package com.example.movie_db

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.collections.ArrayList

class FragmentOne : Fragment() {
    private lateinit var adapter: AdapterForMovies
    private lateinit var movies: List<Movie>
    private lateinit var recView: RecyclerView
    private lateinit var swipeCase: SwipeRefreshLayout

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: ViewGroup = inflater
            .inflate(R.layout.fragments_activity,
                container, false) as ViewGroup

        val toolbar: TextView = rootView.findViewById(R.id.toolbar)
        toolbar.text = "Movies"

        recView = rootView.findViewById(R.id.recycler_view)
        recView.layoutManager = LinearLayoutManager(activity)
        swipeCase = rootView.findViewById(R.id.main_content)
        swipeCase.setOnRefreshListener {
            viewsOnInit()
        }
        viewsOnInit()
        return rootView
    }

    @SuppressLint("ShowToast")
    private fun jsonOnLoad() {
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                return
            }
            RetrofitService.getPostApi().getMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN)
                .enqueue(object : Callback<MovieResponse> {
                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        swipeCase.isRefreshing = false
                    }

                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        Log.d("postList", response.body().toString())
                        if (response.isSuccessful) {
                            val list = response.body()?.getResults()
                            adapter.movies = list as List<Movie>
                            adapter.notifyDataSetChanged()
                        }
                        swipeCase.isRefreshing = false
                    }
                })
        } catch (e: Exception){
            Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT)
        }
    }

    private fun viewsOnInit(){
        movies = ArrayList()
        this.adapter = activity?.applicationContext?.let { AdapterForMovies(it, movies) }!!
        recView.layoutManager = GridLayoutManager(activity, 3)
        recView.itemAnimator= DefaultItemAnimator()
        recView.adapter = this.adapter
        this.adapter.notifyDataSetChanged()

        jsonOnLoad()
    }
}
