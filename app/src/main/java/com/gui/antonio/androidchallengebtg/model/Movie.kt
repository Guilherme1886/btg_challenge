package com.gui.antonio.androidchallengebtg.model

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class Movie : Serializable {
    val id:Int? = null
    val title: String? = null
    val poster_path: String? = null
    val release_date: String? = null
    val overview: String? = null
    val vote_average: Float? = null
    val genre_ids: List<Int>? = null

    companion object {
        val KEY_MOVIE = "title"
        val KEY_GENRES = "genres"
    }

    fun year(): String {
        val s = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(release_date)
        val c = Calendar.getInstance()
        c.time = s
        return c.get(Calendar.YEAR).toString()
    }

}