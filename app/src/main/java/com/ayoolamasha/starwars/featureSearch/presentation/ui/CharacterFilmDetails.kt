package com.ayoolamasha.starwars.featureSearch.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.ayoolamasha.starwars.databinding.FragmentCharacterFilmsDetailsBinding
import com.ayoolamasha.starwars.featureSearch.presentation.viewmodel.CharacterDetailsSharedViewModel
import com.ayoolamasha.starwars.featureSearch.presentation.viewmodel.CharacterSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFilmDetails : Fragment(){
    private lateinit var binding: FragmentCharacterFilmsDetailsBinding
    private val characterDetailsSharedViewModel: CharacterDetailsSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterFilmsDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filmDetails = characterDetailsSharedViewModel.filmDetails
        binding.backArrow.setOnClickListener {
            binding.root.findNavController().navigateUp()
        }
    }
}