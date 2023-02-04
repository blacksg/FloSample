package com.blacksg.flosample.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blacksg.flosample.databinding.ActivityMusicPlayerBinding
import com.blacksg.flosample.viewmodel.MusicPlayerViewModel

class MusicPlayerActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMusicPlayerBinding
    private val viewModel: MusicPlayerViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicPlayerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }
}