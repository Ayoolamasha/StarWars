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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayoolamasha.starwars.R
import com.ayoolamasha.starwars.databinding.FragmentCharacterFilmsBinding
import com.ayoolamasha.starwars.domain.model.Films
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.adapter.FilmsAdapter
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.model.FilmsDetails
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state.FilmsUiState
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel.FilmsViewModel
import com.ayoolamasha.starwars.features.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFilmsFragment : Fragment() {
    private lateinit var binding: FragmentCharacterFilmsBinding
    private val filmsViewModel: FilmsViewModel by  viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var filmsAdapter: FilmsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterFilmsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmsViewModel.makeFilmsCall(
            filmsUrl = sharedViewModel.characterDetails?.films,
            characterName = sharedViewModel.characterDetails?.name.toString()
        )

        /**
         * FILMS RESULT STATE
         */
        viewLifecycleOwner.lifecycleScope.launch {
            filmsViewModel.filmsUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { filmsState(it) }
        }
    }

    private fun initFilmsAdapter() {
        filmsAdapter = FilmsAdapter {filmClicked -> onFilmClicked(films = filmClicked)}

        binding.filmsRecycler.isVisible = true
        binding.filmsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = filmsAdapter
        }
    }

    private fun filmsState(filmsUiState: FilmsUiState) {
        if (filmsUiState.isLoading) {
            binding.characterProfileProgress.isVisible = true
            binding.errorLinear.isVisible = false
        } else if (filmsUiState.isErrorResult) {
            binding.characterProfileProgress.isVisible = false
            binding.filmsRecycler.isVisible = false
            errorRetry(errorMessage = filmsUiState.error.toString())
        } else if (filmsUiState.isSuccessResult) {
            if (filmsUiState.films.isNullOrEmpty()){
                binding.errorLinear.isVisible = true
            }else{
                binding.characterProfileProgress.isVisible = false
                binding.errorLinear.isVisible = false
                initFilmsAdapter()
                filmsAdapter.submitList(filmsUiState.films)
            }

        }
    }


    private fun errorRetry(errorMessage: String){
        binding.characterProfileProgress.isVisible = false
        binding.errorLinear.isVisible = true
        binding.errorMessage.text = errorMessage
        binding.retryButton.setOnClickListener {
            filmsViewModel.makeFilmsCall(
                filmsUrl = sharedViewModel.characterDetails?.films,
                characterName = sharedViewModel.characterDetails?.name.toString()
            )
        }

    }

    private fun onFilmClicked(films: Films){
        val action = FilmsDetails(
            title = films.title,
            openingCrawl = films.openingCrawl
        )

        sharedViewModel.filmDetails = action
        binding.root.findNavController().navigate(R.id.action_characterDetailsViewHolderFragment_to_characterFilmDetails)


    }


}