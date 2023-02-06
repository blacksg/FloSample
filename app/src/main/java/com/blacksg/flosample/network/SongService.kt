package com.blacksg.flosample.network

import com.blacksg.flosample.model.Song
import retrofit2.Call
import retrofit2.http.GET

interface SongService {
    @GET("song.json")
    fun getSong(): Call<Song>
}