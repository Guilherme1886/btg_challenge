package com.gui.antonio.androidchallengebtg.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gui.antonio.androidchallengebtg.R
import com.gui.antonio.androidchallengebtg.model.Movie
import com.gui.antonio.androidchallengebtg.networkconfig.RetrofitConfig
import java.text.SimpleDateFormat
import java.util.*

class MovieAdapter(val context: Context, val listMovies: List<Movie>?, val listener: onClickMovie?) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return if (listMovies!!.isNotEmpty()) listMovies.size else 0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        listMovies?.get(position)?.let { holder.bind(it) }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val posterImageView = itemView.findViewById<ImageView>(R.id.posterImageView)
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val yearTextView = itemView.findViewById<TextView>(R.id.yearTextView)

        fun bind(item: Movie) {
            nameTextView.text = item.title
            yearTextView.text = item.year()
            Glide.with(context).load("${RetrofitConfig.URL_BASE_IMAGES}${item.poster_path}").into(posterImageView)
            itemView.setOnClickListener { listener?.onClick(item) }
        }
    }

    interface onClickMovie {
        fun onClick(item: Movie)
    }
}