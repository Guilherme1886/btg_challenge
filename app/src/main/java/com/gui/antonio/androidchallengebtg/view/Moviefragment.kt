package com.gui.antonio.androidchallengebtg.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.gui.antonio.androidchallengebtg.R
import com.gui.antonio.androidchallengebtg.model.Movie
import com.gui.antonio.androidchallengebtg.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_moviefragment.*

class Moviefragment(val movies: ArrayList<Movie>, val mainViewModel: MainViewModel) : Fragment(),
    MovieAdapter.onClickMovie {

    private var movieAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_moviefragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadMovies()
    }

    private fun loadMovies() {
        recycler_view.setHasFixedSize(true)
        recycler_view.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler_view.layoutManager = LinearLayoutManager(activity)
        movieAdapter = activity?.let { MovieAdapter(it, movies, this) }
        recycler_view.adapter = movieAdapter
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

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater?) {

        menuInflater?.inflate(R.menu.menu, menu)

        val searchItem = menu?.findItem(R.id.search_view)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }


}
