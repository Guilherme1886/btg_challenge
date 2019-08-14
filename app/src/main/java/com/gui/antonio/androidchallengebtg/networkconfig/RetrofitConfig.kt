package com.gui.antonio.androidchallengebtg.networkconfig

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    val URL_BASE = "https://api.themoviedb.org/"
    val URL_BASE_IMAGES = "https://image.tmdb.org/t/p/w200/"

    var retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create<MoviesService>(MoviesService::class.java)
}