package com.blacksg.flosample.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blacksg.flosample.network.SongService
import com.blacksg.flosample.model.Song
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MusicPlayerViewModel : ViewModel() {
    
    val song = MutableLiveData<Song>()
    
    fun requestSong() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SongService::class.java).getSong()
        service.enqueue(object : Callback<Song> {
            override fun onResponse(
                call: Call<Song>,
                response: Response<Song>
            ) {
                val songValue = response.body() ?: Song.EMPTY
                song.postValue(songValue)
            }
            
            override fun onFailure(
                call: Call<Song>,
                t: Throwable
            ) {
                Log.d("REQUEST_FAILED", "Throwable: ${t.message}")
            }
        })
    }
    
    companion object {
        const val BASE_URL = "https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/2020-flo/"
    }
}