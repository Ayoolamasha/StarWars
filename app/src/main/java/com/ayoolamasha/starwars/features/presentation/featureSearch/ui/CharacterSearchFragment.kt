package com.ayoolamasha.starwars.features.presentation.featureSearch.ui

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
import com.ayoolamasha.starwars.core.extensions.closeListener
import com.ayoolamasha.starwars.core.extensions.openDeleteAllCharactersPopUpDialog
import com.ayoolamasha.starwars.core.extensions.queryTextListener
import com.ayoolamasha.starwars.core.extensions.showSnackBar
import com.ayoolamasha.starwars.core.extensions.statusBarColor
import com.ayoolamasha.starwars.databinding.FragmentSearchBinding
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.features.presentation.featureSearch.adapter.RecentSearchesAdapter
import com.ayoolamasha.starwars.features.presentation.featureSearch.adapter.SearchAdapter
import com.ayoolamasha.starwars.features.presentation.featureSearch.model.CharacterDetails
import com.ayoolamasha.starwars.features.presentation.featureSearch.state.CharacterSearchUiState
import com.ayoolamasha.starwars.features.presentation.featureSearch.viewmodel.CharacterSearchViewModel
import com.ayoolamasha.starwars.features.presentation.viewmodel.SearchHistoryDataBaseViewModel
import com.ayoolamasha.starwars.features.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterSearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val characterSearchViewModel: CharacterSearchViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val searchHistoryDataBaseViewModel: SearchHistoryDataBaseViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recentSearchesAdapter: RecentSearchesAdapter
    private var searchHistory: List<Characters> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusBarColor()

        /**
         * SEARCH RESULT STATE
         */
        viewLifecycleOwner.lifecycleScope.launch {
            characterSearchViewModel.characterSearchUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { characterSearchState(it) }
        }

        /**
         * GET SEARCH HISTORY
         */
        viewLifecycleOwner.lifecycleScope.launch {
            searchHistoryDataBaseViewModel.getAllCharacters()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    searchHistoryDB(it)
                    searchHistory = it
                }
        }



        binding.searchText.queryTextListener({ searchQuery ->
            characterSearchViewModel.makeCharacterSearchCall(searchParams = searchQuery.toString())
            binding.shouldShowSearchResult = true
        }, { searchHistoryDB(characters = searchHistory) })

        binding.searchText.closeListener { searchHistoryDB(characters = searchHistory) }

        binding.includeRecentSearch.deleteAllCharacters.setOnClickListener {
            openDeleteAllCharactersPopUpDialog {
                searchHistoryDataBaseViewModel.deleteAllData()
                showSnackBar(resources.getString(R.string.delete_success))
            }
        }
    }


    private fun initSearchAdapter() {
        searchAdapter = SearchAdapter { onCharacterClicked(it) }

        binding.includeSearchResult.searchResultRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = searchAdapter
        }
    }

    private fun initRecentSearchAdapter() {
        recentSearchesAdapter = RecentSearchesAdapter { onCharacterClicked(it) }

        binding.includeRecentSearch.recentSearchRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = recentSearchesAdapter
        }
    }


    private fun characterSearchState(characterSearchUiState: CharacterSearchUiState) {
        if (characterSearchUiState.isLoading) {
            binding.shouldShowLottie = false
            binding.shouldShowRecentSearch = false

            binding.includeSearchResult.searchCharacterProgress.isVisible = true
            binding.includeSearchResult.searchResultRecycler.isVisible = false
            binding.includeSearchResult.errorLinear.isVisible = false
        } else if (characterSearchUiState.isErrorResult) {
            binding.shouldShowLottie = false
            binding.shouldShowRecentSearch = false
            binding.shouldShowSearchResult = true

            binding.includeSearchResult.searchCharacterProgress.isVisible = false
            binding.includeSearchResult.searchResultRecycler.isVisible = false
            binding.includeSearchResult.errorLinear.isVisible = true
            binding.includeSearchResult.errorMessage.text = characterSearchUiState.error
        } else if (characterSearchUiState.isSuccessResult) {
            binding.shouldShowSearchResult = true
            binding.shouldShowRecentSearch = false
            binding.shouldShowLottie = false

            binding.includeSearchResult.searchCharacterProgress.isVisible = false
            binding.includeSearchResult.errorLinear.isVisible = false
            binding.includeSearchResult.searchResultRecycler.isVisible = true
            initSearchAdapter()
            searchAdapter.submitList(characterSearchUiState.characterSearchUICase)


        }

    }

    private fun searchHistoryDB(characters: List<Characters>) {
        if (characters.isEmpty()) {
            binding.shouldShowLottie = true
            binding.shouldShowSearchResult = false
            binding.shouldShowRecentSearch = false
        } else {
            binding.shouldShowSearchResult = false
            binding.shouldShowRecentSearch = true
            initRecentSearchAdapter()
            recentSearchesAdapter.submitList(characters)
        }
    }

    private fun onCharacterClicked(characters: Characters) {
        val action = CharacterDetails(
            name = characters.name,
            birthYear = characters.birthYear,
            films = characters.films,
            height = characters.height,
            homeWorld = characters.homeWorld,
            species = characters.species,
            url = characters.url
        )

        sharedViewModel.characterDetails = action
        binding.root.findNavController()
            .navigate(R.id.action_characterSearchFragment_to_characterDetailsViewHolderFragment)

    }
}