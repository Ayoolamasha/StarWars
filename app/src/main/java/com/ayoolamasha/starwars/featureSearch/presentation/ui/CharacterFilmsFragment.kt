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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayoolamasha.starwars.R
import com.ayoolamasha.starwars.databinding.FragmentCharacterFilmsBinding
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsDetails
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.presentation.adapter.FilmsAdapter
import com.ayoolamasha.starwars.featureSearch.presentation.state.FilmsUiState
import com.ayoolamasha.starwars.featureSearch.presentation.viewmodel.CharacterDetailsSharedViewModel
import com.ayoolamasha.starwars.featureSearch.presentation.viewmodel.CharacterSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CharacterFilmsFragment : Fragment() {
    private lateinit var binding: FragmentCharacterFilmsBinding
    private val characterSearchViewModel: CharacterSearchViewModel by viewModels()
    private val characterDetailsSharedViewModel: CharacterDetailsSharedViewModel by activityViewModels()
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
        characterSearchViewModel.makeFilmsCall(
            filmsUrl = characterDetailsSharedViewModel.characterDetails?.films,
            characterName = characterDetailsSharedViewModel.characterDetails?.name.toString()
        )

        /**
         * FILMS RESULT STATE
         */
        viewLifecycleOwner.lifecycleScope.launch {
            characterSearchViewModel.filmsUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { filmsState(it) }
        }
    }

    private fun initFilmsAdapter() {
        filmsAdapter = FilmsAdapter {filmClicked -> onFilmClicked(filmsUiCase = filmClicked)}

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
            errorRetry(errorMessage = filmsUiState.error.toString())
        } else if (filmsUiState.isSuccessResult) {
            if (filmsUiState.filmsUiCase.isNullOrEmpty()){
                binding.errorLinear.isVisible = true
            }else{
                binding.characterProfileProgress.isVisible = false
                binding.errorLinear.isVisible = false
                initFilmsAdapter()
                filmsAdapter.submitList(filmsUiState.filmsUiCase)
            }

        }
    }


    private fun errorRetry(errorMessage: String){
        binding.characterProfileProgress.isVisible = false
        binding.errorLinear.isVisible = true
        binding.errorMessage.text = errorMessage
        binding.retryButton.setOnClickListener {
            characterSearchViewModel.makeFilmsCall(
                filmsUrl = characterDetailsSharedViewModel.characterDetails?.films,
                characterName = characterDetailsSharedViewModel.characterDetails?.name.toString()
            )
        }

    }

    private fun onFilmClicked(filmsUiCase: FilmsUiCase){
        val action = FilmsDetails(
            title = filmsUiCase.title,
            openingCrawl = filmsUiCase.openingCrawl
        )

        characterDetailsSharedViewModel.filmDetails = action
        binding.root.findNavController().navigate(R.id.action_characterDetailsViewHolderFragment_to_characterFilmDetails)

    }


}