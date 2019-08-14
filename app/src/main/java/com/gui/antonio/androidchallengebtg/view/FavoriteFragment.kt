package com.gui.antonio.androidchallengebtg.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.gui.antonio.androidchallengebtg.R
import com.gui.antonio.androidchallengebtg.model.Movie
import com.gui.antonio.androidchallengebtg.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment(var favorites: List<Movie>, val mainViewModel: MainViewModel) : Fragment(),
    MovieAdapter.onClickMovie {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadMovies()
    }

    private fun loadMovies() {
        recycler_view.setHasFixedSize(true)
        recycler_view.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler_view.layoutManager = LinearLayoutManager(activity)
        val movieAdapter = activity?.let { MovieAdapter(it, favorites, this) }
        recycler_view.adapter = movieAdapter
    }

    override fun onResume() {
        super.onResume()

        val shared = activity?.getSharedPreferences(MainActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val list = shared?.getStringSet(MainActivity.SHARED_PREF_KEY, hashSetOf())

        favorites = mainViewModel.moviesFavorites(list)
        recycler_view.adapter?.notifyDataSetChanged()
    }

    override fun onClick(item: Movie) {

        val genres = mainViewModel.returnGenres(item.genre_ids)

        startActivity(
            Intent(activity, MovieDetailActivity::class.java).putExtra(
                Movie.KEY_MOVIE,
                item
            ).putExtra(Movie.KEY_GENRES, genres)
        )

    }


}
