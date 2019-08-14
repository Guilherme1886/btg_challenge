package com.gui.antonio.androidchallengebtg.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gui.antonio.androidchallengebtg.networkconfig.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository {

    fun getMovies(): LiveData<Results> {
        val data = MutableLiveData<Results>()
        RetrofitConfig.service.getMovies().enqueue(object : Callback<Results> {
            override fun onFailure(call: Call<Results>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<Results>?, response: Response<Results>?) {
                if (response?.isSuccessful as Boolean) {
                    data.value = response.body()
                } else if (response.code() != 200) {
                    data.postValue(null)
                }
            }
        })
        return data
    }

    fun getGenres(): LiveData<Genres> {
        val data = MutableLiveData<Genres>()
        RetrofitConfig.service.getGenres().enqueue(object : Callback<Genres> {
            override fun onFailure(call: Call<Genres>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<Genres>?, response: Response<Genres>?) {
                if (response?.isSuccessful as Boolean) {
                    data.value = response.body()
                }
            }
        })
        return data
    }

}