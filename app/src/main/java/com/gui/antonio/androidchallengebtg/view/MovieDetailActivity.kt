package com.gui.antonio.androidchallengebtg.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.KeyListener
import android.util.Log
import android.widget.CompoundButton
import com.bumptech.glide.Glide
import com.gui.antonio.androidchallengebtg.R
import com.gui.antonio.androidchallengebtg.model.Movie
import com.gui.antonio.androidchallengebtg.networkconfig.RetrofitConfig
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private var item: Movie? = null
    private val favoritesList = HashSet<String>()
    private var shared: SharedPreferences? = null
    private var list: Set<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        shared = getSharedPreferences(MainActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        list = shared?.getStringSet(MainActivity.SHARED_PREF_KEY, hashSetOf())

        loadDetails()
    }

    private fun loadDetails() {

        item = intent.getSerializableExtra(Movie.KEY_MOVIE) as Movie
        val genres = intent.getStringArrayListExtra(Movie.KEY_GENRES)

        Glide.with(this).load("${RetrofitConfig.URL_BASE_IMAGES}${item?.poster_path}").into(posterImageView)
        titleTextView.text = item?.title
        sinopseTextView.text = item?.overview
        noteTextView.text = item?.vote_average.toString()
        genresTextView.text = genres.toString().toUpperCase()
        favoriteSwitch.setOnCheckedChangeListener(this)

        verifyFavorite()
    }

    private fun verifyFavorite() {

        list?.forEach {

            favoritesList.add(it)

            if (it == item?.id.toString()) {
                favoriteSwitch.isChecked = true
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        if (isChecked) {

            favoritesList.add(item?.id.toString())
        } else {
            favoritesList.remove(item?.id.toString())
        }

        shared?.edit()?.putStringSet(MainActivity.SHARED_PREF_KEY, favoritesList)?.apply()
    }

}
