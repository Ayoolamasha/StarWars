package com.ayoolamasha.starwars.featureSearch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayoolamasha.starwars.databinding.ActivitySearchNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchNavHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}