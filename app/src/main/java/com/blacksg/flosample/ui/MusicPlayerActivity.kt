package com.blacksg.flosample.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blacksg.flosample.R
import com.blacksg.flosample.databinding.ActivityMusicPlayerBinding
import com.bumptech.glide.Glide

class MusicPlayerActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMusicPlayerBinding
    private val viewModel: MusicPlayerViewModel by viewModels()
    private var mediaPlayer: MediaPlayer? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicPlayerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        
        observeSong()
        viewModel.requestSong()
        binding.playButton.setOnClickListener {
            togglePlayButton()
        }
    }
    
    private fun observeSong() {
        viewModel.song.observe(this) { song ->
            binding.song = song
            Glide.with(this)
                .load(song.image)
                .centerCrop()
                .into(binding.albumImage)
            prepareMediaPlayer(song.file)
        }
    }
    
    private fun prepareMediaPlayer(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(
                this@MusicPlayerActivity,
                Uri.parse(url)
            )
            prepare()
        }
    }
    
    private fun togglePlayButton() {
        val drawable = if (mediaPlayer?.isPlaying ?: return) {
            mediaPlayer?.pause()
            R.drawable.button_img_play
        } else {
            mediaPlayer?.start()
            R.drawable.button_img_pause
        }
        binding.playButton.setImageResource(drawable)
    }
    
    override fun onStop() {
        mediaPlayer?.pause()
        super.onStop()
    }
    
    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}