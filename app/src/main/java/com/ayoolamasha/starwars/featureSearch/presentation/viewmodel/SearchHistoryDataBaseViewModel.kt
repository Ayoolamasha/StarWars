package com.ayoolamasha.starwars.featureSearch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoolamasha.starwars.featureSearch.domain.domainRepository.cacheDomain.CharacterCacheDomainRepository
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchHistoryDataBaseViewModel @Inject constructor(
    private val characterCacheDomainRepository: CharacterCacheDomainRepository
): ViewModel(){

    fun getAllCharacters(): Flow<List<CharacterUICase>> {
        return characterCacheDomainRepository.domainGetAllCharacter()
    }

    fun deleteAllData() = viewModelScope.launch {
        characterCacheDomainRepository.domainDeleteAllCharacters()
        characterCacheDomainRepository.domainDeleteAllFilms()
        characterCacheDomainRepository.domainDeleteAllPlanets()
        characterCacheDomainRepository.domainDeleteAllSpecies()
    }


}