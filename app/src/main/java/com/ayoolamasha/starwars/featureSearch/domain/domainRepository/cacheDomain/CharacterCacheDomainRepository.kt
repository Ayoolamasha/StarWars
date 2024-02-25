package com.ayoolamasha.starwars.featureSearch.domain.domainRepository.cacheDomain

import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import kotlinx.coroutines.flow.Flow

interface CharacterCacheDomainRepository {

    /**
     * CHARACTERS
     */

    fun domainGetAllCharacter(): Flow<List<CharacterUICase>>

    suspend fun domainDeleteAllCharacters()

    /**
     * PLANET
     */

    suspend fun domainDeleteAllPlanets()

    /**
     * SPECIES
     */
    suspend fun domainDeleteAllSpecies()

    /**
     * FILMS
     */
    suspend fun domainDeleteAllFilms()
}