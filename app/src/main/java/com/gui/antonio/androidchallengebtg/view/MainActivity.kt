package com.gui.antonio.androidchallengebtg.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import com.gui.antonio.androidchallengebtg.R
import com.gui.antonio.androidchallengebtg.model.Genres
import com.gui.antonio.androidchallengebtg.model.Movie
import com.gui.antonio.androidchallengebtg.model.MoviesRepository
import com.gui.antonio.androidchallengebtg.model.Results
import com.gui.antonio.androidchallengebtg.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Observer<Results> {

    private val fragment = ArrayList<Fragment>()
    private var viewModel = MainViewModel(MoviesRepository())
    var shared: SharedPreferences? = null
    var list: Set<String>? = null

    companion object {
        val SHARED_PREF_NAME = "favorites"
        val SHARED_PREF_KEY = "ids"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel.movies.observe(this, this)
        shared = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        list = shared?.getStringSet(SHARED_PREF_KEY, hashSetOf())

    }

    override fun onChanged(movies: Results) {

        hideProgress()

        if (movies.results == null) {
            Toast.makeText(this, "Houve algum problema", Toast.LENGTH_LONG).show()
        } else {
            loadView(movies)
        }
    }

    private fun loadView(movies: Results) {

        fragment.add(Moviefragment(movies.results!!, viewModel))
        fragment.add(FavoriteFragment(viewModel.moviesFavorites(list), viewModel))

        viewPager.adapter = MoviePagerAdapter()
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun hideProgress() {
        progress_circular.visibility = View.INVISIBLE
    }

    inner class MoviePagerAdapter : FragmentPagerAdapter(supportFragmentManager) {

        val titleOne = "Filmes Populares"
        val titleTwo = "Meus Filmes Favoritos"

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return titleOne
                1 -> return titleTwo
            }
            return super.getPageTitle(position)
        }

        override fun getItem(position: Int): Fragment {
            return fragment[position]
        }

        override fun getCount(): Int {
            return fragment.size
        }

    }

}
