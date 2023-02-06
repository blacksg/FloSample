package com.blacksg.flosample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Song(
    @SerializedName("title")
    val title: String,
    
    @SerializedName("singer")
    val singer: String,
    
    @SerializedName("album")
    val album: String,
    
    @SerializedName("duration")
    val duration: Int,
    
    @SerializedName("image")
    val image: String,
    
    @SerializedName("file")
    val file: String,
    
    @SerializedName("lyrics")
    val lyrics: String
) : Serializable {
    companion object {
        val EMPTY = Song(
            title = "",
            singer = "",
            album = "",
            duration = -1,
            image = "",
            file = "",
            lyrics = ""
        )
    }
}