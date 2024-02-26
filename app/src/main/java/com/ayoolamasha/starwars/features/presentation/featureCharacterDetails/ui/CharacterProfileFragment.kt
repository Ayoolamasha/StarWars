package com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.ui

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
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state.CharacterUiState
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state.PlanetUiState
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state.SpeciesUiState
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel.CharacterViewModel
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel.PlanetViewModel
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel.SpeciesViewModel
import com.ayoolamasha.starwars.features.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterProfileFragment : Fragment(){
    private lateinit var binding: FragmentCharacterProfileBinding
    private val planetViewModel: PlanetViewModel by viewModels()
    private val speciesViewModel : SpeciesViewModel by viewModels()
    private val characterViewModel: CharacterViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
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
        makeSpecieCall()
        makePlanetCall()
        makeCharacterCall()




        /**
         * CHARACTER PROFILE RESULT STATE
         */
        viewLifecycleOwner.lifecycleScope.launch {
            characterViewModel.characterUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect{characterState(it)}
        }


        /**
         * PLANET RESULT STATE
         */
        viewLifecycleOwner.lifecycleScope.launch {
            planetViewModel.planetUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect{planetState(it)}
        }


        /**
         * SPECIE RESULT STATE
         */
        viewLifecycleOwner.lifecycleScope.launch {
            speciesViewModel.speciesUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect{speciesState(it)}
        }
    }


    private fun makeCharacterCall(){
        characterViewModel.makeCharacterCall(
            characterUrl = sharedViewModel.characterDetails?.url.toString(),
            characterName = sharedViewModel.characterDetails?.name.toString(),
        )
    }

    private fun makePlanetCall(){
        if (sharedViewModel.characterDetails?.homeWorld != null){
            planetViewModel.makePlanetCall(
                planetUrl = sharedViewModel.characterDetails?.homeWorld.toString(),
                characterName = sharedViewModel.characterDetails?.name.toString()
            )
        }else{
            binding.planet.visibility = View.GONE
            binding.planetCard.visibility = View.GONE

        }
    }

    private fun makeSpecieCall(){
        if (sharedViewModel.characterDetails?.species.isNullOrEmpty()){
            binding.species.visibility = View.GONE
            binding.speciesCard.visibility = View.GONE

        }else{
            speciesViewModel.makeSpeciesCall(
                speciesUrl = sharedViewModel.characterDetails?.species?.get(0).toString(),
                characterName = sharedViewModel.characterDetails?.name.toString()
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
            binding.profileContent.isVisible = false
            errorRetry(errorMessage = planetUiState.error.toString())

        }else if (planetUiState.isSuccessResult){
            binding.characterProfileProgress.isVisible = false
            binding.errorLinear.isVisible = false
            binding.profileContent.isVisible = true
            binding.planetUi = planetUiState.planets
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
            binding.profileContent.isVisible = false
            errorRetry(errorMessage = speciesUiState.error.toString())

        }else if (speciesUiState.isSuccessResult){
            binding.characterProfileProgress.isVisible = false
            binding.errorLinear.isVisible = false
            binding.profileContent.isVisible = true
            binding.speciesUI = speciesUiState.species
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
            binding.profileContent.isVisible = false
            errorRetry(errorMessage = characterUiState.error.toString())
        }else if (characterUiState.isSuccessResult){
            binding.characterProfileProgress.isVisible = false
            binding.errorLinear.isVisible = false
            binding.profileContent.isVisible = true
            binding.characterUi = characterUiState.characters
            hasFetchedCharacter = true


        }
    }

    private fun errorRetry(errorMessage: String){
        binding.characterProfileProgress.isVisible = false
        binding.errorLinear.isVisible = true
        binding.errorMessage.text = errorMessage
        binding.retryButton.setOnClickListener {
           makeCharacterCall()
            makePlanetCall()
            makeSpecieCall()
        }
    }





}