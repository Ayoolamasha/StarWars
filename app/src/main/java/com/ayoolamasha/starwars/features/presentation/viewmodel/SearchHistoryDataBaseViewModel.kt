package com.ayoolamasha.starwars.features.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.domain.repository.characterRepository.CharacterRepository
import com.ayoolamasha.starwars.domain.repository.filmsRepository.FilmsRepository
import com.ayoolamasha.starwars.domain.repository.planetRepository.PlanetRepository
import com.ayoolamasha.starwars.domain.repository.speciesRepository.SpeciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchHistoryDataBaseViewModel @Inject constructor(
   private val characterRepository: CharacterRepository,
   private val filmsRepository: FilmsRepository,
   private val planetRepository: PlanetRepository,
   private val speciesRepository: SpeciesRepository
): ViewModel(){

    fun getAllCharacters(): Flow<List<Characters>> {
        return characterRepository.getAllResentSearchCharacter()
    }

    fun deleteAllData() = viewModelScope.launch {
        characterRepository.deleteAllCharacters()
        speciesRepository.deleteAllSpecies()
        filmsRepository.deleteAllFilms()
        planetRepository.deleteAllPlanets()
    }


}