package com.gui.antonio.androidchallengebtg.networkconfig

import com.gui.antonio.androidchallengebtg.model.Genres
import com.gui.antonio.androidchallengebtg.model.Movie
import com.gui.antonio.androidchallengebtg.model.Results
import retrofit2.Call
import retrofit2.http.GET

interface MoviesService {

    @GET("3/movie/popular?api_key=121de94efea839b23840f0ac45726c83&language=en-US&page=1")
    fun getMovies(): Call<Results>

    @GET("3/genre/movie/list?api_key=121de94efea839b23840f0ac45726c83&language=en-US&page=1")
    fun getGenres(): Call<Genres>
}
