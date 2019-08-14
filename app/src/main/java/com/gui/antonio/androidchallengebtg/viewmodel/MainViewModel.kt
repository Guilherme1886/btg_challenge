package com.gui.antonio.androidchallengebtg.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gui.antonio.androidchallengebtg.model.Genres
import com.gui.antonio.androidchallengebtg.model.Movie
import com.gui.antonio.androidchallengebtg.model.MoviesRepository
import com.gui.antonio.androidchallengebtg.model.Results

class MainViewModel(moviesRepository: MoviesRepository) : ViewModel() {
    val movies: LiveData<Results> = moviesRepository.getMovies()
    val genres: LiveData<Genres> = moviesRepository.getGenres()


    fun returnGenres(ids: List<Int>?): ArrayList<String> {

        val list = arrayListOf<String>()

        ids?.forEach { id ->

            genres.value?.genres?.forEach {
                if (it.id == id) {
                    list.add(it.name.toString())
                }
            }

        }


        return list
    }

    fun moviesFavorites(favorites: Set<String>?): ArrayList<Movie> {

        val list = arrayListOf<Movie>()

        favorites?.forEach { f ->

            movies.value?.results?.forEach {
                val isFavorite = f.toInt() == it.id
                if (isFavorite) list.add(it)
            }

        }
        return list
    }
}