package com.ayoolamasha.starwars.featureSearch.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ayoolamasha.starwars.core.extensions.statusBarColor
import com.ayoolamasha.starwars.databinding.FragmentCharacterProfileBinding
import com.ayoolamasha.starwars.featureSearch.presentation.state.CharacterUiState
import com.ayoolamasha.starwars.featureSearch.presentation.state.PlanetUiState
import com.ayoolamasha.starwars.featureSearch.presentation.state.SpeciesUiState
import com.ayoolamasha.starwars.featureSearch.presentation.viewmodel.CharacterDetailsSharedViewModel
import com.ayoolamasha.starwars.featureSearch.presentation.viewmodel.CharacterSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterProfileFragment : Fragment(){
    private lateinit var binding: FragmentCharacterProfileBinding
    private val characterSearchViewModel: CharacterSearchViewModel by viewModels()
    private val characterDetailsSharedViewModel: CharacterDetailsSharedViewModel by activityViewModels()
    private var hasFetchedCharacter = false
    private var hasFetchedSpecies = false
    private var hasFetchedPlanet = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentCharacterProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusBarColor()
        makeSpeciePlanetCall()

        characterSearchViewModel.makeCharacterCall(
            characterUrl = characterDetailsSharedViewModel.characterDetails?.url.toString(),
            characterName = characterDetailsSharedViewModel.characterDetails?.name.toString(),
        )


        /**
         * CHARACTER PROFILE RESULT STATE
         */
        viewLifecycleOwner.lifecycleScope.launch {
            characterSearchViewModel.characterUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect{characterState(it)}
        }


        /**
         * PLANET RESULT STATE
         */
        viewLifecycleOwner.lifecycleScope.launch {
            characterSearchViewModel.planetUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect{planetState(it)}
        }


        /**
         * SPECIE RESULT STATE
         */
        viewLifecycleOwner.lifecycleScope.launch {
            characterSearchViewModel.speciesUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect{speciesState(it)}
        }
    }



    private fun makeSpeciePlanetCall(){
        if (characterDetailsSharedViewModel.characterDetails?.homeWorld != null){
            characterSearchViewModel.makePlanetCall(
                planetUrl = characterDetailsSharedViewModel.characterDetails?.homeWorld.toString(),
                characterName = characterDetailsSharedViewModel.characterDetails?.name.toString()
            )
        }else{
            binding.planet.visibility = View.GONE
            binding.planetCard.visibility = View.GONE

        }

        if (characterDetailsSharedViewModel.characterDetails?.species.isNullOrEmpty()){
            binding.species.visibility = View.GONE
            binding.speciesCard.visibility = View.GONE

        }else{
            characterSearchViewModel.makeSpeciesCall(
                speciesUrl = characterDetailsSharedViewModel.characterDetails?.species?.get(0).toString(),
                characterName = characterDetailsSharedViewModel.characterDetails?.name.toString()
            )
        }
    }

    private fun planetState(planetUiState: PlanetUiState){
        if (planetUiState.isLoading){
            binding.characterProfileProgress.isVisible = true
            binding.errorLinear.isVisible = false
            hasFetchedPlanet = false
        }else if (planetUiState.isErrorResult){
            binding.characterProfileProgress.isVisible = false
            errorRetry(errorMessage = planetUiState.error.toString())

        }else if (planetUiState.isSuccessResult){
            binding.characterProfileProgress.isVisible = false
            binding.errorLinear.isVisible = false
            binding.profileContent.isVisible = true
            binding.planetUi = planetUiState.planetUiCase
            hasFetchedPlanet = true

        }

    }

    private fun speciesState(speciesUiState: SpeciesUiState){
        if (speciesUiState.isLoading){
            binding.characterProfileProgress.isVisible = true
            binding.errorLinear.isVisible = false
            hasFetchedSpecies = false
        }else if (speciesUiState.isErrorResult){
            binding.characterProfileProgress.isVisible = false
            errorRetry(errorMessage = speciesUiState.error.toString())

        }else if (speciesUiState.isSuccessResult){
            binding.characterProfileProgress.isVisible = false
            binding.errorLinear.isVisible = false
            binding.profileContent.isVisible = true
            binding.speciesUI = speciesUiState.speciesUiCase
            hasFetchedSpecies = true


        }
    }

    private fun characterState(characterUiState: CharacterUiState){
        if (characterUiState.isLoading){
            binding.characterProfileProgress.isVisible = true
            binding.errorLinear.isVisible = false
            hasFetchedCharacter = false
        }else if (characterUiState.isErrorResult){
            binding.characterProfileProgress.isVisible = false
            errorRetry(errorMessage = characterUiState.error.toString())
        }else if (characterUiState.isSuccessResult){
            binding.characterProfileProgress.isVisible = false
            binding.errorLinear.isVisible = false
            binding.profileContent.isVisible = true
            binding.characterUi = characterUiState.characterUICase
            hasFetchedCharacter = true


        }
    }

    private fun errorRetry(errorMessage: String){
        binding.characterProfileProgress.isVisible = false
        binding.errorLinear.isVisible = true
        binding.errorMessage.text = errorMessage
        binding.retryButton.setOnClickListener {
            makeSpeciePlanetCall()
            characterSearchViewModel.makeCharacterCall(
                characterUrl = characterDetailsSharedViewModel.characterDetails?.url.toString(),
                characterName = characterDetailsSharedViewModel.characterDetails?.name.toString(),
            )
        }
    }





}